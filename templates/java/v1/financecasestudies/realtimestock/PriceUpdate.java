package financecasestudies.realtimestock;

public class PriceUpdate {
    private final String symbol;
    private final double price;
    private final long timestamp;

    public PriceUpdate(String symbol, double price, long timestamp) {
        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException("symbol is required");
        }
        if (price < 0) {
            throw new IllegalArgumentException("price must be non-negative");
        }
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
