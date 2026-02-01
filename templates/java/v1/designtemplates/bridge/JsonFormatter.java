package designtemplates.bridge;

public class JsonFormatter implements Formatter {
    private static final String JSON_PREFIX = "{\"label\":\"";
    private static final String JSON_MIDDLE = "\",\"message\":\"";
    private static final String JSON_SUFFIX = "\"}";

    @Override
    public String format(String label, String message) {
        return JSON_PREFIX + label + JSON_MIDDLE + message + JSON_SUFFIX;
    }
}
