package designtemplates.strategy;

public class GreeterContext {
    private GreeterStrategy strategy;

    public GreeterContext(GreeterStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(GreeterStrategy strategy) {
        this.strategy = strategy;
    }

    public String greet(String name) {
        return strategy.format(name);
    }
}
