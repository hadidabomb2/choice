from __future__ import annotations

from dataclasses import dataclass
from typing import Protocol

FRIENDLY_TEMPLATE = "Hey {name}, great to meet you."
FORMAL_TEMPLATE = "Hello {name}. It is a pleasure to meet you."
UPPERCASE_TEMPLATE = "Hello {name} from strategy"
NAME_TOKEN = "{name}"


class GreeterStrategy(Protocol):
    def format(self, name: str) -> str:
        ...


@dataclass(slots=True)
class FriendlyStrategy:
    def format(self, name: str) -> str:
        return _format_template(FRIENDLY_TEMPLATE, name)


@dataclass(slots=True)
class FormalStrategy:
    def format(self, name: str) -> str:
        return _format_template(FORMAL_TEMPLATE, name)


@dataclass(slots=True)
class UppercaseStrategy:
    def format(self, name: str) -> str:
        return _format_template(UPPERCASE_TEMPLATE, name).upper()


@dataclass(slots=True)
class GreeterContext:
    strategy: GreeterStrategy

    def set_strategy(self, strategy: GreeterStrategy) -> None:
        self.strategy = strategy

    def greet(self, name: str) -> str:
        return self.strategy.format(name)


def _format_template(template: str, name: str) -> str:
    return template.replace(NAME_TOKEN, name)
