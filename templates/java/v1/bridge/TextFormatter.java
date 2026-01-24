package bridge;

public class TextFormatter implements Formatter {
    @Override
    public String format(String label, String message) {
        return label + ": " + message;
    }
}
