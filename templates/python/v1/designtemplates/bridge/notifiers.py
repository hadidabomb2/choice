from __future__ import annotations

from dataclasses import dataclass

from bridge.formatters import Formatter

EMAIL_LABEL = "email"
SMS_LABEL = "sms"


@dataclass(slots=True)
class Notifier:
    formatter: Formatter

    def notify(self, message: str) -> str:
        raise NotImplementedError


@dataclass(slots=True)
class EmailNotifier(Notifier):
    def notify(self, message: str) -> str:
        return self.formatter.format(EMAIL_LABEL, message)


@dataclass(slots=True)
class SmsNotifier(Notifier):
    def notify(self, message: str) -> str:
        return self.formatter.format(SMS_LABEL, message)
