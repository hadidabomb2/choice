package financecasestudies.paymentprocessing;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryIdempotencyStore implements IdempotencyStore {
    private final Set<String> processed = ConcurrentHashMap.newKeySet();

    @Override
    public boolean alreadyProcessed(String transactionId) {
        return processed.contains(transactionId);
    }

    @Override
    public void markProcessed(String transactionId) {
        processed.add(transactionId);
    }
}
