package adapter;

public class LegacyLogger {
    private static final String LEGACY_PREFIX = "LEGACY: ";

    public void write(String message) {
        System.out.println(LEGACY_PREFIX + message);
    }
}
