package designtemplates.state;

public class DocumentContext {
    private State state;

    public DocumentContext(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String process(String input) {
        return state.handle(input);
    }

    public String currentState() {
        return state.name();
    }
}
