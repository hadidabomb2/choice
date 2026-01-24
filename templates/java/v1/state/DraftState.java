package state;

public class DraftState implements State {
    private static final String STATE_NAME = "draft";
    private static final String DRAFT_PREFIX = "DRAFT: ";

    @Override
    public String handle(String input) {
        return DRAFT_PREFIX + input;
    }

    @Override
    public String name() {
        return STATE_NAME;
    }
}
