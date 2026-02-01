package designtemplates.observer;

public class LoggingObserver implements Observer {
    private static final String OBSERVER_PREFIX = "Observer ";
    private static final String SAW_INFIX = " saw ";
    private static final String MESSAGE_SEPARATOR = ": ";
    private final String name;

    public LoggingObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String event, String payload) {
        System.out.println(OBSERVER_PREFIX + name + SAW_INFIX + event + MESSAGE_SEPARATOR + payload);
    }
}
