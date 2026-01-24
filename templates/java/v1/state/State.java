package state;

public interface State {
    String handle(String input);
    String name();
}
