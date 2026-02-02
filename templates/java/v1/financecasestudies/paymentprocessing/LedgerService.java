package financecasestudies.paymentprocessing;

import java.util.Objects;

public class LedgerService {
    private final LedgerStore ledgerStore;
    private final IdempotencyStore idempotencyStore;
    private final Object lock = new Object();

    public LedgerService(LedgerStore ledgerStore, IdempotencyStore idempotencyStore) {
        this.ledgerStore = Objects.requireNonNull(ledgerStore, "ledgerStore");
        this.idempotencyStore = Objects.requireNonNull(idempotencyStore, "idempotencyStore");
    }

    public void transfer(TransactionRequest request) {
        validate(request);

        if (idempotencyStore.alreadyProcessed(request.getTransactionId())) {
            return;
        }

        synchronized (lock) {
            if (idempotencyStore.alreadyProcessed(request.getTransactionId())) {
                return;
            }

            Account from = ledgerStore.getAccount(request.getFromAccountId());
            Account to = ledgerStore.getAccount(request.getToAccountId());
            if (from == null || to == null) {
                throw new IllegalArgumentException("Unknown account");
            }

            if (from.getBalanceCents() < request.getAmountCents()) {
                throw new InsufficientFundsException("Insufficient balance");
            }

            from.debit(request.getAmountCents());
            to.credit(request.getAmountCents());
            ledgerStore.saveAccount(from);
            ledgerStore.saveAccount(to);

            ledgerStore.record(new TransactionRecord(
                request.getTransactionId(),
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmountCents(),
                System.currentTimeMillis()
            ));
            idempotencyStore.markProcessed(request.getTransactionId());
        }
    }

    private void validate(TransactionRequest request) {
        Objects.requireNonNull(request, "request");
        if (request.getTransactionId() == null || request.getTransactionId().isBlank()) {
            throw new IllegalArgumentException("transactionId is required");
        }
        if (request.getFromAccountId() == null || request.getFromAccountId().isBlank()) {
            throw new IllegalArgumentException("fromAccountId is required");
        }
        if (request.getToAccountId() == null || request.getToAccountId().isBlank()) {
            throw new IllegalArgumentException("toAccountId is required");
        }
        if (request.getAmountCents() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (request.getFromAccountId().equals(request.getToAccountId())) {
            throw new IllegalArgumentException("Accounts must be different");
        }
    }
}
