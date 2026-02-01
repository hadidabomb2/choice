package designtemplates.abstractfactory;

public class DarkCheckbox implements Checkbox {
    private static final String LABEL = "DarkCheckbox";

    @Override
    public String render() {
        return LABEL;
    }
}
