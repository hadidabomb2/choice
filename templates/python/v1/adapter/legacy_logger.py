from __future__ import annotations


class LegacyLogger:
    def write(self, message: str) -> None:
        print(f"LEGACY: {message}")
