package financecasestudies.ratelimiter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucket implements RateLimiter {
    private final int capacity;
    private final long refillIntervalMs;
    private int tokens;
    private long lastRefillTime;
    private final Lock lock = new ReentrantLock();

    public TokenBucket(int capacity, long refillIntervalMs) {
        this.capacity = capacity;
        this.refillIntervalMs = refillIntervalMs;
        this.tokens = capacity;
        this.lastRefillTime = System.currentTimeMillis();
    }

    @Override
    public boolean tryAcquire() {
        lock.lock();
        try {
            refill();
            if (tokens > 0) {
                tokens--;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastRefillTime;
        long tokensToAdd = (int) (elapsed / refillIntervalMs);
        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, (int)(tokens + tokensToAdd));
            lastRefillTime = now;
        }
    }
}