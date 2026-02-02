package financecasestudies.paymentprocessing;

public class TransactionRequest {
    private final String transactionId;
    private final String fromAccountId;
    private final String toAccountId;
    private final long amountCents;

    public TransactionRequest(
        String transactionId,
        String fromAccountId,
        String toAccountId,
        long amountCents
    ) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amountCents = amountCents;
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
}
