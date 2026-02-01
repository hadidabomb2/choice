package designtemplates.command;

public class AppendTextCommand implements Command {
    private final TextBuffer buffer;
    private final String text;

    public AppendTextCommand(TextBuffer buffer, String text) {
        this.buffer = buffer;
        this.text = text;
    }

    @Override
    public String execute() {
        buffer.append(text);
        return buffer.snapshot();
    }
}
