package strategy;

public class UppercaseStrategy implements GreeterStrategy {
    @Override
    public String format(String name) {
        return ("HELLO " + name + " FROM STRATEGY").toUpperCase();
    }
}
