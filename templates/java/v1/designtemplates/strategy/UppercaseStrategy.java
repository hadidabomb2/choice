package designtemplates.strategy;

public class UppercaseStrategy implements GreeterStrategy {
    private static final String PREFIX = "HELLO ";
    private static final String SUFFIX = " FROM STRATEGY";

    @Override
    public String format(String name) {
        return (PREFIX + name + SUFFIX).toUpperCase();
    }
}
