package designtemplates.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ElementNode implements Node {
    private final String tag;
    private final List<Node> children = new ArrayList<>();

    public ElementNode(String tag) {
        this.tag = tag;
    }

    public ElementNode add(Node child) {
        children.add(child);
        return this;
    }

    @Override
    public String render() {
        StringJoiner joiner = new StringJoiner("");
        for (Node child : children) {
            joiner.add(child.render());
        }
        return renderElement(tag, joiner.toString());
    }

    private String renderElement(String tag, String content) {
        return "<" + tag + ">" + content + "</" + tag + ">";
    }
}
