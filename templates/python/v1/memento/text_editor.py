from __future__ import annotations

from dataclasses import dataclass

EMPTY_BUFFER = ""


@dataclass(frozen=True, slots=True)
class TextSnapshot:
    state: str


class TextEditor:
    def __init__(self) -> None:
        self._buffer = EMPTY_BUFFER

    def append(self, text: str) -> None:
        self._buffer += text

    def current(self) -> str:
        return self._buffer

    def save(self) -> TextSnapshot:
        return TextSnapshot(self.current())

    def restore(self, snapshot: TextSnapshot) -> None:
        self._buffer = snapshot.state
