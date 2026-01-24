from __future__ import annotations

class AppConfig:
    _instance: "AppConfig | None" = None

    def __new__(cls) -> "AppConfig":
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance._values = {}  # type: ignore[attr-defined]
        return cls._instance

    def set(self, key: str, value: str) -> None:
        self._values[key] = value

    def get(self, key: str, default_value: str = "") -> str:
        return self._values.get(key, default_value)
