package designtemplates.visitor;

public interface NodeVisitor<T> {
    T visitText(TextNode text);
    T visitElement(ElementNode element);
}
