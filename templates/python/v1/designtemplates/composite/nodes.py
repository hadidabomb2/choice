from __future__ import annotations

from dataclasses import dataclass, field
from typing import List, Protocol


class Node(Protocol):
    def render(self) -> str:
        ...


@dataclass(slots=True)
class TextNode:
    text: str

    def render(self) -> str:
        return self.text


@dataclass(slots=True)
class ElementNode:
    tag: str
    children: List[Node] = field(default_factory=list)

    def add(self, child: Node) -> "ElementNode":
        self.children.append(child)
        return self

    def render(self) -> str:
        body = "".join(child.render() for child in self.children)
        return _render_element(self.tag, body)


def _render_element(tag: str, body: str) -> str:
    return f"<{tag}>{body}</{tag}>"
