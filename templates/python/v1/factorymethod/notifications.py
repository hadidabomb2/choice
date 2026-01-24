from __future__ import annotations

from dataclasses import dataclass

EMAIL_PREFIX = "Email: "
SMS_PREFIX = "SMS: "


@dataclass(slots=True)
class EmailNotification:
    def send(self, message: str) -> str:
        return f"{EMAIL_PREFIX}{message}"


@dataclass(slots=True)
class SmsNotification:
    def send(self, message: str) -> str:
        return f"{SMS_PREFIX}{message}"


class NotificationCreator:
    def notify(self, message: str) -> str:
        notification = self.create_notification()
        return notification.send(message)

    def create_notification(self) -> "EmailNotification | SmsNotification":
        raise NotImplementedError


class EmailNotificationCreator(NotificationCreator):
    def create_notification(self):
        return EmailNotification()


class SmsNotificationCreator(NotificationCreator):
    def create_notification(self):
        return SmsNotification()
