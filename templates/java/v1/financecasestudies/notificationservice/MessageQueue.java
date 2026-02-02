package financecasestudies.notificationservice;

public interface MessageQueue {
    void publish(NotificationMessage message);
    void subscribe(MessageHandler handler);
}
