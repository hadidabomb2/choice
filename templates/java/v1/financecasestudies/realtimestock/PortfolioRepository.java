package financecasestudies.realtimestock;

import java.util.Collection;

public interface PortfolioRepository {
    Portfolio load(String userId);
    void save(Portfolio portfolio);
    Collection<Portfolio> listAll();
}
