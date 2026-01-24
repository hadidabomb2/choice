from __future__ import annotations

from dataclasses import dataclass

from bridge.formatters import Formatter


@dataclass(slots=True)
class Notifier:
    formatter: Formatter

    def notify(self, message: str) -> str:
        raise NotImplementedError


@dataclass(slots=True)
class EmailNotifier(Notifier):
    def notify(self, message: str) -> str:
        return self.formatter.format("email", message)


@dataclass(slots=True)
class SmsNotifier(Notifier):
    def notify(self, message: str) -> str:
        return self.formatter.format("sms", message)
