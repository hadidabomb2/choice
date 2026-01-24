package observer;

public class LoggingObserver implements Observer {
    private final String name;

    public LoggingObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String event, String payload) {
        System.out.println("Observer " + name + " saw " + event + ": " + payload);
    }
}
