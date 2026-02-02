package financecasestudies.realtimestock;

import java.util.Map;
import java.util.Objects;

public class PortfolioValuationService {
    private final PriceCache priceCache;

    public PortfolioValuationService(PriceCache priceCache) {
        this.priceCache = Objects.requireNonNull(priceCache, "priceCache");
    }

    public double calculate(Portfolio portfolio) {
        Objects.requireNonNull(portfolio, "portfolio");
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : portfolio.getHoldings().entrySet()) {
            PriceUpdate price = priceCache.get(entry.getKey());
            if (price != null) {
                total += entry.getValue() * price.getPrice();
            }
        }
        return total;
    }
}
