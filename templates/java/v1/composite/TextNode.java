package composite;

public class TextNode implements Node {
    private final String text;

    public TextNode(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return text;
    }
}
