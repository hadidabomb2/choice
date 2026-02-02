package financecasestudies.paymentprocessing;

public class TransactionRecord {
    private final String transactionId;
    private final String fromAccountId;
    private final String toAccountId;
    private final long amountCents;
    private final long timestamp;

    public TransactionRecord(
        String transactionId,
        String fromAccountId,
        String toAccountId,
        long amountCents,
        long timestamp
    ) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amountCents = amountCents;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public long getAmountCents() {
        return amountCents;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
