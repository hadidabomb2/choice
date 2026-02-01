package designtemplates.adapter;

public class LegacyLoggerAdapter implements Logger {
    private final LegacyLogger legacy;

    public LegacyLoggerAdapter(LegacyLogger legacy) {
        this.legacy = legacy;
    }

    @Override
    public void log(String level, String message) {
        legacy.write(formatMessage(level, message));
    }

    private String formatMessage(String level, String message) {
        return "[" + level + "] " + message;
    }
}
