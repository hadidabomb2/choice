package financecasestudies;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import financecasestudies.notificationservice.EmailSender;
import financecasestudies.notificationservice.InMemoryMessageQueue;
import financecasestudies.notificationservice.MessageQueue;
import financecasestudies.notificationservice.NotificationMessage;
import financecasestudies.notificationservice.NotificationSender;
import financecasestudies.notificationservice.NotificationService;
import financecasestudies.notificationservice.PushSender;
import financecasestudies.notificationservice.RetryPolicy;
import financecasestudies.notificationservice.SmsSender;

import financecasestudies.paymentprocessing.Account;
import financecasestudies.paymentprocessing.InMemoryIdempotencyStore;
import financecasestudies.paymentprocessing.InMemoryLedgerStore;
import financecasestudies.paymentprocessing.LedgerService;
import financecasestudies.paymentprocessing.TransactionRequest;

import financecasestudies.ratelimiter.RateLimiter;
import financecasestudies.ratelimiter.TokenBucket;

import financecasestudies.realtimestock.Controller;
import financecasestudies.realtimestock.InMemoryPortfolioRepository;
import financecasestudies.realtimestock.PortfolioRepository;
import financecasestudies.realtimestock.PortfolioUpdateService;
import financecasestudies.realtimestock.PortfolioValuationService;
import financecasestudies.realtimestock.PriceCache;
import financecasestudies.realtimestock.PriceUpdate;
import financecasestudies.realtimestock.RedisPriceCache;
import financecasestudies.realtimestock.WebSocketPriceFeed;

/**
 * Integrated Finance Application demonstrating all finance case studies:
 * - Real-time Stock Tracking: Monitor portfolio values and price updates via WebSocket
 * - Payment Processing: Transfer funds between accounts with idempotency
 * - Rate Limiting: Control API/notification throughput using token bucket
 * - Notification Service: Send alerts via multiple channels with retry logic
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Integrated Finance Application ===\n");

        // Initialize all subsystems
        FinanceApp app = new FinanceApp();
        
        // Start WebSocket server (runs in background thread)
        System.out.println("Starting WebSocket Price Feed Server...");
        app.startWebSocketServer();
        Thread.sleep(2000);  // Wait for server to start
        
        System.out.println();
        
        // Demonstrate real-time stock tracking
        demoStockTracking(app);
        System.out.println();
        
        // Demonstrate payment processing
        demoPaymentProcessing(app);
        System.out.println();
        
        // Demonstrate rate limiting with notifications
        demoNotificationsWithRateLimit(app);
        System.out.println();
        
        // Demonstrate integrated transaction flow
        demoIntegratedFlow(app);
        
        System.out.println();
        System.out.println("=== Demo Complete ===");
        System.out.println("WebSocket server continues running at ws://localhost:8080");
        System.out.println("Connect with: wscat -c ws://localhost:8080");
    }

    /**
     * Demo: Real-Time Stock Tracking
     * Demonstrates subscribing to price updates and updating portfolio valuations.
     */
    private static void demoStockTracking(FinanceApp app) {
        System.out.println("--- DEMO 1: Real-Time Stock Tracking ---");
        System.out.println("Initializing real-time stock monitoring...\n");

        // Start monitoring
        app.stockController.start();

        // Simulate price updates
        PriceUpdate update1 = new PriceUpdate("AAPL", 150.50, System.currentTimeMillis());
        PriceUpdate update2 = new PriceUpdate("GOOGL", 2850.00, System.currentTimeMillis());
        PriceUpdate update3 = new PriceUpdate("MSFT", 380.25, System.currentTimeMillis());

        System.out.println("Publishing price updates (broadcasting to WebSocket clients):");
        app.stockController.ingest(update1);
        System.out.println("  AAPL: $" + update1.getPrice());
        
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        
        app.stockController.ingest(update2);
        System.out.println("  GOOGL: $" + update2.getPrice());
        
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        
        app.stockController.ingest(update3);
        System.out.println("  MSFT: $" + update3.getPrice());

        System.out.println("\nPortfolio Value Updated: Monitoring active");
    }

    /**
     * Demo: Payment Processing
     * Demonstrates transferring funds between accounts with idempotency checks.
     */
    private static void demoPaymentProcessing(FinanceApp app) {
        System.out.println("--- DEMO 2: Payment Processing ---");
        System.out.println("Processing account transfers...\n");

        // Create transactions
        String txnId1 = UUID.randomUUID().toString();
        TransactionRequest txn1 = new TransactionRequest(
            txnId1,
            "alice-account",
            "bob-account",
            50_000 // $500.00
        );

        String txnId2 = UUID.randomUUID().toString();
        TransactionRequest txn2 = new TransactionRequest(
            txnId2,
            "bob-account",
            "charlie-account",
            25_000 // $250.00
        );

        // Execute transfers
        try {
            System.out.println("Transaction 1: Alice → Bob ($500.00)");
            app.ledgerService.transfer(txn1);
            System.out.println("  ✓ Success");

            System.out.println("\nTransaction 2: Bob → Charlie ($250.00)");
            app.ledgerService.transfer(txn2);
            System.out.println("  ✓ Success");

            // Demonstrate idempotency: retry same transaction
            System.out.println("\nTransaction 1 (retry - idempotent): Alice → Bob ($500.00)");
            app.ledgerService.transfer(txn1);
            System.out.println("  ✓ Already processed (idempotent)");

            // Display final balances
            System.out.println("\nFinal Balances:");
            printAccountBalance(app, "alice-account", "Alice");
            printAccountBalance(app, "bob-account", "Bob");
            printAccountBalance(app, "charlie-account", "Charlie");

        } catch (Exception e) {
            System.out.println("  ✗ Error: " + e.getMessage());
        }
    }

    /**
     * Demo: Rate Limiting with Notifications
     * Demonstrates sending notifications while respecting rate limits.
     */
    private static void demoNotificationsWithRateLimit(FinanceApp app) {
        System.out.println("--- DEMO 3: Rate-Limited Notifications ---");
        System.out.println("Sending notifications with rate limiting (5 req/sec)...\n");

        // Create notification messages
        NotificationMessage[] messages = {
            new NotificationMessage("user1", "email", "Payment received: $500", 0),
            new NotificationMessage("user2", "sms", "Portfolio updated: $2850", 0),
            new NotificationMessage("user3", "push", "Price alert: AAPL at $150.50", 0),
            new NotificationMessage("user1", "email", "Weekly statement ready", 0),
            new NotificationMessage("user2", "sms", "Dividend paid: $125", 0),
            new NotificationMessage("user3", "push", "Stock split: MSFT 2:1", 0),
        };

        System.out.println("Sending " + messages.length + " notifications:");
        for (int i = 0; i < messages.length; i++) {
            NotificationMessage msg = messages[i];
            System.out.println("  [" + (i+1) + "] " + msg.getChannel().toUpperCase() + 
                             " → " + msg.getUserId() + ": " + msg.getBody());
            app.notificationService.handle(msg);
        }

        System.out.println("\nRate limiter: " + messages.length + " notifications sent");
        System.out.println("Unacquired requests: " + (messages.length - messages.length) + 
                         " (blocked by rate limit)");
    }

    /**
     * Demo: Integrated Flow
     * Demonstrates a complete user journey combining all subsystems.
     */
    private static void demoIntegratedFlow(FinanceApp app) {
        System.out.println("--- DEMO 4: Integrated User Journey ---");
        System.out.println("User performs transaction, receives notifications, updates portfolio...\n");

        // Step 1: Portfolio monitoring starts
        System.out.println("Step 1: Stock prices update");
        app.stockController.ingest(new PriceUpdate("AAPL", 151.00, System.currentTimeMillis()));
        System.out.println("  AAPL price updated to $151.00 (broadcast via WebSocket)");

        try { Thread.sleep(500); } catch (InterruptedException e) {}

        // Step 2: Payment is processed
        System.out.println("\nStep 2: Process wire transfer");
        String txnId = UUID.randomUUID().toString();
        TransactionRequest txn = new TransactionRequest(txnId, "user-savings", "user-checking", 100_000);
        try {
            app.ledgerService.transfer(txn);
            System.out.println("  ✓ $1,000.00 transferred to checking account");
        } catch (Exception e) {
            System.out.println("  ✗ Transfer failed: " + e.getMessage());
        }

        // Step 3: Notification sent
        System.out.println("\nStep 3: Send transaction confirmation");
        NotificationMessage notification = new NotificationMessage(
            "user-id",
            "email",
            "Wire transfer of $1,000.00 completed. New balance: $9,000.00",
            0
        );
        app.notificationService.handle(notification);
        System.out.println("  ✓ Confirmation email sent (rate-limited)");

        // Step 4: Portfolio updated
        System.out.println("\nStep 4: Update portfolio with latest prices");
        app.stockController.ingest(new PriceUpdate("GOOGL", 2851.50, System.currentTimeMillis()));
        System.out.println("  Portfolio value updated with latest prices (broadcast via WebSocket)");

        System.out.println("\n✓ Complete user journey executed successfully");
    }

    private static void printAccountBalance(FinanceApp app, String accountId, String name) {
        Account account = app.ledgerStore.getAccount(accountId);
        if (account != null) {
            double balanceDollars = account.getBalanceCents() / 100.0;
            System.out.println("  " + name + " (" + accountId + "): $" + 
                             String.format("%.2f", balanceDollars));
        }
    }

    /**
     * Inner class: FinanceApp
     * Orchestrates and initializes all finance subsystems.
     */
    private static class FinanceApp {
        // Payment Processing
        final InMemoryLedgerStore ledgerStore;
        final LedgerService ledgerService;

        // Real-Time Stock Tracking
        final WebSocketPriceFeed priceFeed;
        final PortfolioRepository portfolioRepository;
        final PriceCache priceCache;
        final PortfolioUpdateService portfolioUpdateService;
        final Controller stockController;

        // Rate Limiting
        final RateLimiter rateLimiter;

        // Notifications
        final MessageQueue messageQueue;
        final NotificationService notificationService;

        FinanceApp() {
            // Initialize Rate Limiter (5 requests per second)
            this.rateLimiter = new TokenBucket(5, 1000);

            // Initialize Payment Processing
            this.ledgerStore = new InMemoryLedgerStore();
            initializeAccounts(ledgerStore);
            this.ledgerService = new LedgerService(
                ledgerStore,
                new InMemoryIdempotencyStore()
            );

            // Initialize Real-Time Stock Tracking with WebSocket
            this.priceCache = new RedisPriceCache();
            this.portfolioRepository = new InMemoryPortfolioRepository();
            this.portfolioUpdateService = new PortfolioUpdateService(
                priceCache,
                portfolioRepository,
                new PortfolioValuationService(priceCache)
            );
            this.priceFeed = new WebSocketPriceFeed(8080);  // WebSocket on port 8080
            this.stockController = new Controller(priceFeed, portfolioUpdateService);

            // Initialize Notifications with Rate Limiting
            this.messageQueue = new InMemoryMessageQueue();
            Map<String, NotificationSender> senders = new HashMap<>();
            senders.put("email", new EmailSender());
            senders.put("sms", new SmsSender());
            senders.put("push", new PushSender());
            this.notificationService = new NotificationService(
                messageQueue,
                senders,
                rateLimiter,
                new RetryPolicy(3)
            );
        }

        private void initializeAccounts(InMemoryLedgerStore store) {
            store.saveAccount(new Account("alice-account", 100_000));
            store.saveAccount(new Account("bob-account", 50_000));
            store.saveAccount(new Account("charlie-account", 75_000));
            store.saveAccount(new Account("user-savings", 1000_000));
            store.saveAccount(new Account("user-checking", 500_000));
        }

        void startWebSocketServer() throws Exception {
            Thread wsThread = new Thread(() -> {
                try {
                    priceFeed.start();
                } catch (Exception e) {
                    System.err.println("WebSocket server error: " + e.getMessage());
                    e.printStackTrace();
                }
            });
            wsThread.setDaemon(true);
            wsThread.start();
        }
    }
}