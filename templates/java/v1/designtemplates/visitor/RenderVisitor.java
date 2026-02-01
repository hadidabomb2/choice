package designtemplates.visitor;

public class RenderVisitor implements NodeVisitor<String> {
    @Override
    public String visitText(TextNode text) {
        return text.getText();
    }

    @Override
    public String visitElement(ElementNode element) {
        String inner = renderChildren(element);
        return renderElement(element.getTag(), inner);
    }

    private String renderChildren(ElementNode element) {
        StringBuilder sb = new StringBuilder();
        for (Node child : element.getChildren()) {
            sb.append(child.accept(this));
        }
        return sb.toString();
    }

    private String renderElement(String tag, String inner) {
        return "<" + tag + ">" + inner + "</" + tag + ">";
    }
}
