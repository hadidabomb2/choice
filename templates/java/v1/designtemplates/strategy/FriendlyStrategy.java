package designtemplates.strategy;

public class FriendlyStrategy implements GreeterStrategy {
    private static final String PREFIX = "Hey ";
    private static final String SUFFIX = ", great to meet you.";

    @Override
    public String format(String name) {
        return PREFIX + name + SUFFIX;
    }
}
