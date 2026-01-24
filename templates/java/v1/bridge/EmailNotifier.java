package bridge;

public class EmailNotifier extends Notifier {
    private static final String EMAIL_LABEL = "email";

    public EmailNotifier(Formatter formatter) {
        super(formatter);
    }

    @Override
    public String notify(String message) {
        return formatter.format(EMAIL_LABEL, message);
    }
}
