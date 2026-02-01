package designtemplates.factorymethod;

public class SmsNotification implements Notification {
    private static final String SMS_PREFIX = "SMS: ";

    @Override
    public String send(String message) {
        return SMS_PREFIX + message;
    }
}
