package financecasestudies.realtimestock;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPortfolioRepository implements PortfolioRepository {
    private final Map<String, Portfolio> store = new ConcurrentHashMap<>();

    @Override
    public Portfolio load(String userId) {
        return store.get(userId);
    }

    @Override
    public void save(Portfolio portfolio) {
        Objects.requireNonNull(portfolio, "portfolio");
        store.put(portfolio.getUserId(), portfolio);
    }

    @Override
    public Collection<Portfolio> listAll() {
        return store.values();
    }
}
