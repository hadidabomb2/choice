package financecasestudies.realtimestock;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RedisPriceCache implements PriceCache {
    private final Map<String, PriceUpdate> prices = new ConcurrentHashMap<>();

    @Override
    public void put(PriceUpdate update) {
        Objects.requireNonNull(update, "update");
        prices.put(update.getSymbol(), update);
    }

    @Override
    public PriceUpdate get(String symbol) {
        return prices.get(symbol);
    }
}
