package bridge;

public class JsonFormatter implements Formatter {
    @Override
    public String format(String label, String message) {
        return "{\"label\":\"" + label + "\",\"message\":\"" + message + "\"}";
    }
}
