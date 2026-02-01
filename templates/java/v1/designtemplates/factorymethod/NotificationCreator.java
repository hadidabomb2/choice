package designtemplates.factorymethod;

public abstract class NotificationCreator {
    public String notify(String message) {
        Notification notification = createNotification();
        return notification.send(message);
    }

    protected abstract Notification createNotification();
}
