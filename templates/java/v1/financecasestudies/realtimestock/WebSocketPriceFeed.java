package financecasestudies.realtimestock;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class WebSocketPriceFeed implements PriceFeed {
    private final List<PriceListener> listeners = new CopyOnWriteArrayList<>();

    @Override
    public void subscribe(PriceListener listener) {
        listeners.add(Objects.requireNonNull(listener, "listener"));
    }

    @Override
    public void unsubscribe(PriceListener listener) {
        listeners.remove(Objects.requireNonNull(listener, "listener"));
    }

    @Override
    public void publish(PriceUpdate update) {
        Objects.requireNonNull(update, "update");
        for (PriceListener listener : listeners) {
            listener.onPrice(update);
        }
    }
}
