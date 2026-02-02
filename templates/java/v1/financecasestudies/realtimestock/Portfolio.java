package financecasestudies.realtimestock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Portfolio {
    private final String userId;
    private final Map<String, Integer> holdings;
    private double lastValue;

    public Portfolio(String userId, Map<String, Integer> holdings) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        this.userId = userId;
        this.holdings = new HashMap<>(Objects.requireNonNull(holdings, "holdings"));
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, Integer> getHoldings() {
        return Collections.unmodifiableMap(holdings);
    }

    public double getLastValue() {
        return lastValue;
    }

    public void setLastValue(double lastValue) {
        this.lastValue = lastValue;
    }
}
