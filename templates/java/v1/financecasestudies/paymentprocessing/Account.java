package financecasestudies.paymentprocessing;

public class Account {
    private final String id;
    private long balanceCents;

    public Account(String id, long balanceCents) {
        this.id = id;
        this.balanceCents = balanceCents;
    }

    public String getId() {
        return id;
    }

    public long getBalanceCents() {
        return balanceCents;
    }

    public void debit(long amountCents) {
        if (amountCents <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balanceCents -= amountCents;
    }

    public void credit(long amountCents) {
        if (amountCents <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balanceCents += amountCents;
    }
}
