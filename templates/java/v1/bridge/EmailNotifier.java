package bridge;

public class EmailNotifier extends Notifier {
    public EmailNotifier(Formatter formatter) {
        super(formatter);
    }

    @Override
    public String notify(String message) {
        return formatter.format("email", message);
    }
}
