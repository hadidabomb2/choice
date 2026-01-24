package visitor;

public class RenderVisitor implements NodeVisitor<String> {
    @Override
    public String visitText(TextNode text) {
        return text.getText();
    }

    @Override
    public String visitElement(ElementNode element) {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(element.getTag()).append(">");
        for (Node child : element.getChildren()) {
            sb.append(child.accept(this));
        }
        sb.append("</").append(element.getTag()).append(">");
        return sb.toString();
    }
}
