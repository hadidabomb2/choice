package financecasestudies.notificationservice;

public class EmailSender implements NotificationSender {
    @Override
    public boolean send(NotificationMessage message) {
        return true;
    }
}
