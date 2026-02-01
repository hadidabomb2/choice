package designtemplates.abstractfactory;

public class LightButton implements Button {
    private static final String LABEL = "LightButton";

    @Override
    public String render() {
        return LABEL;
    }
}
