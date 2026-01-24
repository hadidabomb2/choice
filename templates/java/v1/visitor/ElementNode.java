package visitor;

import java.util.ArrayList;
import java.util.List;

public class ElementNode implements Node {
    private final String tag;
    private final List<Node> children = new ArrayList<>();

    public ElementNode(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public List<Node> getChildren() {
        return children;
    }

    public ElementNode add(Node child) {
        children.add(child);
        return this;
    }

    @Override
    public <T> T accept(NodeVisitor<T> visitor) {
        return visitor.visitElement(this);
    }
}
