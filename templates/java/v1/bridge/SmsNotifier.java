package bridge;

public class SmsNotifier extends Notifier {
    public SmsNotifier(Formatter formatter) {
        super(formatter);
    }

    @Override
    public String notify(String message) {
        return formatter.format("sms", message);
    }
}
