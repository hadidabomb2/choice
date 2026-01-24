from __future__ import annotations

from dataclasses import dataclass
from typing import Protocol


class State(Protocol):
    def handle(self, input_text: str) -> str:
        ...

    def name(self) -> str:
        ...


@dataclass(slots=True)
class DraftState:
    def handle(self, input_text: str) -> str:
        return f"DRAFT: {input_text}"

    def name(self) -> str:
        return "draft"


@dataclass(slots=True)
class PublishedState:
    def handle(self, input_text: str) -> str:
        return f"PUBLISHED: {input_text.upper()}"

    def name(self) -> str:
        return "published"


@dataclass(slots=True)
class DocumentContext:
    state: State

    def set_state(self, state: State) -> None:
        self.state = state

    def process(self, input_text: str) -> str:
        return self.state.handle(input_text)

    def current_state(self) -> str:
        return self.state.name()
