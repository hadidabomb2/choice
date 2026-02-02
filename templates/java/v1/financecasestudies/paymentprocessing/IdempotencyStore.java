package financecasestudies.paymentprocessing;

public interface IdempotencyStore {
    boolean alreadyProcessed(String transactionId);
    void markProcessed(String transactionId);
}
