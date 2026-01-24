package strategy;

public class FormalStrategy implements GreeterStrategy {
    private static final String PREFIX = "Hello ";
    private static final String SUFFIX = ". It is a pleasure to meet you.";

    @Override
    public String format(String name) {
        return PREFIX + name + SUFFIX;
    }
}
