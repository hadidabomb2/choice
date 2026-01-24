package bridge;

public abstract class Notifier {
    protected final Formatter formatter;

    protected Notifier(Formatter formatter) {
        this.formatter = formatter;
    }

    public abstract String notify(String message);
}
