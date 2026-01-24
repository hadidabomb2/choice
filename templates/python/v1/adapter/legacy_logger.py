from __future__ import annotations


LEGACY_PREFIX = "LEGACY: "


class LegacyLogger:
    def write(self, message: str) -> None:
        print(f"{LEGACY_PREFIX}{message}")
