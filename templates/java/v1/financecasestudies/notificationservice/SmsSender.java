package financecasestudies.notificationservice;

public class SmsSender implements NotificationSender {
    @Override
    public boolean send(NotificationMessage message) {
        return true;
    }
}
