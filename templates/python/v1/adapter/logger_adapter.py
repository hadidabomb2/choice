from __future__ import annotations

from dataclasses import dataclass

from adapter.legacy_logger import LegacyLogger


@dataclass(slots=True)
class LegacyLoggerAdapter:
    legacy: LegacyLogger

    def log(self, level: str, message: str) -> None:
        self.legacy.write(_format_message(level, message))


def _format_message(level: str, message: str) -> str:
    return f"[{level}] {message}"
