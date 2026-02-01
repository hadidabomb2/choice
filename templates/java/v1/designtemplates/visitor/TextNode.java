package designtemplates.visitor;

public class TextNode implements Node {
    private final String text;

    public TextNode(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public <T> T accept(NodeVisitor<T> visitor) {
        return visitor.visitText(this);
    }
}
