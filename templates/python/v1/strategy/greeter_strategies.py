from __future__ import annotations

from dataclasses import dataclass
from typing import Protocol


class GreeterStrategy(Protocol):
    def format(self, name: str) -> str:
        ...


@dataclass(slots=True)
class FriendlyStrategy:
    def format(self, name: str) -> str:
        return f"Hey {name}, great to meet you."


@dataclass(slots=True)
class FormalStrategy:
    def format(self, name: str) -> str:
        return f"Hello {name}. It is a pleasure to meet you."


@dataclass(slots=True)
class UppercaseStrategy:
    def format(self, name: str) -> str:
        return f"HELLO {name} FROM STRATEGY".upper()


@dataclass(slots=True)
class GreeterContext:
    strategy: GreeterStrategy

    def set_strategy(self, strategy: GreeterStrategy) -> None:
        self.strategy = strategy

    def greet(self, name: str) -> str:
        return self.strategy.format(name)
