package financecasestudies.ratelimiter;

public class LeakyBucket implements RateLimiter {
    private final double capacity;
    private final double leakRatePerSecond;
    private double stored;
    private long lastLeakMillis;

    public LeakyBucket(double capacity, double leakRatePerSecond) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        if (leakRatePerSecond <= 0) {
            throw new IllegalArgumentException("leakRatePerSecond must be positive");
        }
        this.capacity = capacity;
        this.leakRatePerSecond = leakRatePerSecond;
        this.stored = 0.0;
        this.lastLeakMillis = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean tryAcquire() {
        leak();
        if (stored + 1.0 > capacity) {
            return false;
        }
        stored += 1.0;
        return true;
    }

    private void leak() {
        long now = System.currentTimeMillis();
        double elapsedSeconds = (now - lastLeakMillis) / 1000.0;
        double leaked = elapsedSeconds * leakRatePerSecond;
        if (leaked > 0) {
            stored = Math.max(0.0, stored - leaked);
            lastLeakMillis = now;
        }
    }
}
