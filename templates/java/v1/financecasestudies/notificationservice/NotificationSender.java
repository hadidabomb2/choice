package financecasestudies.notificationservice;

public interface NotificationSender {
    boolean send(NotificationMessage message);
}
