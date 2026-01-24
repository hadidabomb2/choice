from __future__ import annotations

from dataclasses import dataclass, field
from typing import List, Protocol


class NodeVisitor(Protocol):
    def visit_text(self, node: "TextNode") -> str:
        ...

    def visit_element(self, node: "ElementNode") -> str:
        ...


class Node(Protocol):
    def accept(self, visitor: NodeVisitor) -> str:
        ...


@dataclass(slots=True)
class TextNode:
    text: str

    def accept(self, visitor: NodeVisitor) -> str:
        return visitor.visit_text(self)


@dataclass(slots=True)
class ElementNode:
    tag: str
    children: List[Node] = field(default_factory=list)

    def add(self, child: Node) -> "ElementNode":
        self.children.append(child)
        return self

    def accept(self, visitor: NodeVisitor) -> str:
        return visitor.visit_element(self)


@dataclass(slots=True)
class RenderVisitor:
    def visit_text(self, node: TextNode) -> str:
        return node.text

    def visit_element(self, node: ElementNode) -> str:
        inner = "".join(child.accept(self) for child in node.children)
        return f"<{node.tag}>{inner}</{node.tag}>"
