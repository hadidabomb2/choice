package financecasestudies.paymentprocessing;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryLedgerStore implements LedgerStore {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Map<String, TransactionRecord> records = new ConcurrentHashMap<>();

    @Override
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accounts.put(account.getId(), account);
    }

    @Override
    public void record(TransactionRecord record) {
        records.put(record.getTransactionId(), record);
    }
}
