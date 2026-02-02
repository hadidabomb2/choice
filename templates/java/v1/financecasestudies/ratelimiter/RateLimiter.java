package financecasestudies.ratelimiter;

public interface RateLimiter {
    boolean tryAcquire();
}
