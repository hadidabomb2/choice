package state;

public class PublishedState implements State {
    @Override
    public String handle(String input) {
        return "PUBLISHED: " + input.toUpperCase();
    }

    @Override
    public String name() {
        return "published";
    }
}
