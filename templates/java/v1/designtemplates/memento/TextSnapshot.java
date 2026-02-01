package designtemplates.memento;

public class TextSnapshot {
    private final String state;

    public TextSnapshot(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
