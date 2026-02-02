package financecasestudies.ratelimiter;

public class TokenBucket implements RateLimiter {
    private final double capacity;
    private final double refillRatePerSecond;
    private double tokens;
    private long lastRefillMillis;

    public TokenBucket(double capacity, double refillRatePerSecond) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        if (refillRatePerSecond <= 0) {
            throw new IllegalArgumentException("refillRatePerSecond must be positive");
        }
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = capacity;
        this.lastRefillMillis = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean tryAcquire() {
        refill();
        if (tokens < 1.0) {
            return false;
        }
        tokens -= 1.0;
        return true;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        double elapsedSeconds = (now - lastRefillMillis) / 1000.0;
        double refillTokens = elapsedSeconds * refillRatePerSecond;
        if (refillTokens > 0) {
            tokens = Math.min(capacity, tokens + refillTokens);
            lastRefillMillis = now;
        }
    }
}
