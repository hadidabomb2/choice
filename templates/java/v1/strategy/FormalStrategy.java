package strategy;

public class FormalStrategy implements GreeterStrategy {
    @Override
    public String format(String name) {
        return "Hello " + name + ". It is a pleasure to meet you.";
    }
}
