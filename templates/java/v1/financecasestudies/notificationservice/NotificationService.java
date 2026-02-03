package financecasestudies.notificationservice;

import java.util.Map;
import java.util.Objects;

import financecasestudies.ratelimiter.RateLimiter;

public class NotificationService implements MessageHandler {
    private final MessageQueue queue;
    private final Map<String, NotificationSender> senders;
    private final RateLimiter rateLimiter;
    private final RetryPolicy retryPolicy;

    public NotificationService(
        MessageQueue queue,
        Map<String, NotificationSender> senders,
        RateLimiter rateLimiter,
        RetryPolicy retryPolicy
    ) {
        this.queue = Objects.requireNonNull(queue, "queue");
        this.senders = Objects.requireNonNull(senders, "senders");
        this.rateLimiter = Objects.requireNonNull(rateLimiter, "rateLimiter");
        this.retryPolicy = Objects.requireNonNull(retryPolicy, "retryPolicy");
    }

    @Override
    public void handle(NotificationMessage message) {
        Objects.requireNonNull(message, "message");
        if (!rateLimiter.tryAcquire()) {
            queue.publish(message);
            return;
        }

        NotificationSender sender = senders.get(message.getChannel());
        if (sender == null) {
            return;
        }

        boolean success = sender.send(message);
        if (!success && message.getAttempt() < retryPolicy.getMaxRetries()) {
            queue.publish(message.nextAttempt());
        }
    }
}
