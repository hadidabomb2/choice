package strategy;

public class FriendlyStrategy implements GreeterStrategy {
    @Override
    public String format(String name) {
        return "Hey " + name + ", great to meet you.";
    }
}
