package designtemplates.visitor;

public interface Node {
    <T> T accept(NodeVisitor<T> visitor);
}
