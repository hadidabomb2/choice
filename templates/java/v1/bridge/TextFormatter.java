package bridge;

public class TextFormatter implements Formatter {
    private static final String LABEL_SEPARATOR = ": ";

    @Override
    public String format(String label, String message) {
        return label + LABEL_SEPARATOR + message;
    }
}
