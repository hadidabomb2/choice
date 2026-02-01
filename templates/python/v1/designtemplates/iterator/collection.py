from __future__ import annotations

from dataclasses import dataclass, field
from typing import Iterator, List


@dataclass(slots=True)
class Item:
    id: str
    name: str


@dataclass(slots=True)
class ItemCollection:
    items: List[Item] = field(default_factory=list)

    def add(self, item: Item) -> None:
        self.items.append(item)

    def __iter__(self) -> Iterator[Item]:
        return iter(self.items)
