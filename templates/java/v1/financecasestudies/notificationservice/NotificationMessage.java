package financecasestudies.notificationservice;

import java.util.Objects;

public class NotificationMessage {
    private final String userId;
    private final String channel;
    private final String body;
    private final int attempt;

    public NotificationMessage(String userId, String channel, String body, int attempt) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (channel == null || channel.isBlank()) {
            throw new IllegalArgumentException("channel is required");
        }
        this.userId = userId;
        this.channel = channel;
        this.body = Objects.requireNonNull(body, "body");
        if (attempt < 0) {
            throw new IllegalArgumentException("attempt must be non-negative");
        }
        this.attempt = attempt;
    }

    public String getUserId() {
        return userId;
    }

    public String getChannel() {
        return channel;
    }

    public String getBody() {
        return body;
    }

    public int getAttempt() {
        return attempt;
    }

    public NotificationMessage nextAttempt() {
        return new NotificationMessage(userId, channel, body, attempt + 1);
    }
}
