from __future__ import annotations

from dataclasses import dataclass, field
from typing import List, Protocol


class Command(Protocol):
    def execute(self) -> str:
        ...


@dataclass(slots=True)
class TextBuffer:
    buffer: str = ""

    def append(self, text: str) -> None:
        self.buffer += text

    def snapshot(self) -> str:
        return self.buffer


@dataclass(slots=True)
class AppendTextCommand:
    buffer: TextBuffer
    text: str

    def execute(self) -> str:
        self.buffer.append(self.text)
        return self.buffer.snapshot()


@dataclass(slots=True)
class CommandInvoker:
    history: List[Command] = field(default_factory=list)

    def run(self, command: Command) -> str:
        self.history.append(command)
        return command.execute()

    def history_size(self) -> int:
        return len(self.history)
