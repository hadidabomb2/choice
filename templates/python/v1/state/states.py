from __future__ import annotations

from dataclasses import dataclass
from typing import Protocol

DRAFT_STATE_NAME = "draft"
PUBLISHED_STATE_NAME = "published"
DRAFT_PREFIX = "DRAFT: "
PUBLISHED_PREFIX = "PUBLISHED: "


class State(Protocol):
    def handle(self, input_text: str) -> str:
        ...

    def name(self) -> str:
        ...


@dataclass(slots=True)
class DraftState:
    def handle(self, input_text: str) -> str:
        return _format_draft(input_text)

    def name(self) -> str:
        return DRAFT_STATE_NAME


@dataclass(slots=True)
class PublishedState:
    def handle(self, input_text: str) -> str:
        return _format_published(input_text)

    def name(self) -> str:
        return PUBLISHED_STATE_NAME


@dataclass(slots=True)
class DocumentContext:
    state: State

    def set_state(self, state: State) -> None:
        self.state = state

    def process(self, input_text: str) -> str:
        return self.state.handle(input_text)

    def current_state(self) -> str:
        return self.state.name()


def _format_draft(input_text: str) -> str:
    return f"{DRAFT_PREFIX}{input_text}"


def _format_published(input_text: str) -> str:
    return f"{PUBLISHED_PREFIX}{input_text.upper()}"
