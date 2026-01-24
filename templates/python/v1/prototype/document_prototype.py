from __future__ import annotations

from dataclasses import dataclass, field
from typing import List


@dataclass(slots=True)
class DocumentPrototype:
    title: str
    paragraphs: List[str] = field(default_factory=list)

    def add_paragraph(self, paragraph: str) -> None:
        self.paragraphs.append(paragraph)

    def clone_prototype(self) -> "DocumentPrototype":
        return DocumentPrototype(self.title, list(self.paragraphs))

    def summary(self) -> str:
        return _format_summary(self.title, len(self.paragraphs))


def _format_summary(title: str, paragraph_count: int) -> str:
    return f"Document{{title='{title}', paragraphs={paragraph_count}}}"
