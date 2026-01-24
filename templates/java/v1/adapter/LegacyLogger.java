package adapter;

public class LegacyLogger {
    public void write(String message) {
        System.out.println("LEGACY: " + message);
    }
}
