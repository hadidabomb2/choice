package designtemplates.bridge;

public class SmsNotifier extends Notifier {
    private static final String SMS_LABEL = "sms";

    public SmsNotifier(Formatter formatter) {
        super(formatter);
    }

    @Override
    public String notify(String message) {
        return formatter.format(SMS_LABEL, message);
    }
}
