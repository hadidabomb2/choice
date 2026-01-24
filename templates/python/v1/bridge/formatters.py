from __future__ import annotations

from dataclasses import dataclass
from typing import Protocol


class Formatter(Protocol):
    def format(self, label: str, message: str) -> str:
        ...


@dataclass(slots=True)
class TextFormatter:
    def format(self, label: str, message: str) -> str:
        return f"{label}: {message}"


@dataclass(slots=True)
class JsonFormatter:
    def format(self, label: str, message: str) -> str:
        return f'{{"label":"{label}","message":"{message}"}}'
