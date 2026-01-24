package abstractfactory;

public class DarkButton implements Button {
    private static final String LABEL = "DarkButton";

    @Override
    public String render() {
        return LABEL;
    }
}
