package financecasestudies.realtimestock;

public interface PriceFeed {
    void subscribe(PriceListener listener);
    void unsubscribe(PriceListener listener);
    void publish(PriceUpdate update);
}
