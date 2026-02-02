package financecasestudies.paymentprocessing;

public interface LedgerStore {
    Account getAccount(String accountId);
    void saveAccount(Account account);
    void record(TransactionRecord record);
}
