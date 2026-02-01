package designtemplates.factorymethod;

public class EmailNotification implements Notification {
    private static final String EMAIL_PREFIX = "Email: ";

    @Override
    public String send(String message) {
        return EMAIL_PREFIX + message;
    }
}
