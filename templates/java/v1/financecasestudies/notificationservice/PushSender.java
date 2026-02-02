package financecasestudies.notificationservice;

public class PushSender implements NotificationSender {
    @Override
    public boolean send(NotificationMessage message) {
        return true;
    }
}
