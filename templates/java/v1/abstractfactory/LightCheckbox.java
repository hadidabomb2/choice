package abstractfactory;

public class LightCheckbox implements Checkbox {
    private static final String LABEL = "LightCheckbox";

    @Override
    public String render() {
        return LABEL;
    }
}
