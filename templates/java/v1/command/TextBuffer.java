package command;

public class TextBuffer {
    private final StringBuilder buffer = new StringBuilder();

    public void append(String text) {
        buffer.append(text);
    }

    public String snapshot() {
        return buffer.toString();
    }
}
