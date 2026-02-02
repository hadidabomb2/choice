package financecasestudies.realtimestock;

public interface PriceCache {
    void put(PriceUpdate update);
    PriceUpdate get(String symbol);
}
