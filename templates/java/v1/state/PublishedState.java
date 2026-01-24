package state;

public class PublishedState implements State {
    private static final String STATE_NAME = "published";
    private static final String PUBLISHED_PREFIX = "PUBLISHED: ";

    @Override
    public String handle(String input) {
        return PUBLISHED_PREFIX + input.toUpperCase();
    }

    @Override
    public String name() {
        return STATE_NAME;
    }
}
