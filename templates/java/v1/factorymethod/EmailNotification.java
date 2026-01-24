package factorymethod;

public class EmailNotification implements Notification {
    @Override
    public String send(String message) {
        return "Email: " + message;
    }
}
