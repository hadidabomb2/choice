package state;

public class DraftState implements State {
    @Override
    public String handle(String input) {
        return "DRAFT: " + input;
    }

    @Override
    public String name() {
        return "draft";
    }
}
