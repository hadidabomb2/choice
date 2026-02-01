package designtemplates.memento;

public class TextEditor {
    private final StringBuilder buffer = new StringBuilder();

    public void append(String text) {
        buffer.append(text);
    }

    public String current() {
        return buffer.toString();
    }

    public TextSnapshot save() {
        return new TextSnapshot(current());
    }

    public void restore(TextSnapshot snapshot) {
        buffer.setLength(0);
        buffer.append(snapshot.getState());
    }
}
