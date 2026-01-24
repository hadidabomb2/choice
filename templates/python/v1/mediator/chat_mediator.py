from __future__ import annotations

from dataclasses import dataclass, field
from typing import Dict

USER_PREFIX = "User "
RECEIVED_INFIX = " received from "
MESSAGE_SEPARATOR = ": "


class MessageMediator:
    def register(self, participant: "Participant") -> None:
        raise NotImplementedError

    def broadcast(self, sender_id: str, message: str) -> None:
        raise NotImplementedError


@dataclass(slots=True)
class Participant:
    id: str
    mediator: MessageMediator

    def send(self, message: str) -> None:
        self.mediator.broadcast(self.id, message)

    def on_message(self, from_id: str, message: str) -> str:
        raise NotImplementedError


@dataclass(slots=True)
class ChatMediator(MessageMediator):
    participants: Dict[str, Participant] = field(default_factory=dict)

    def register(self, participant: Participant) -> None:
        self.participants[participant.id] = participant

    def broadcast(self, sender_id: str, message: str) -> None:
        for pid, participant in self.participants.items():
            if pid != sender_id:
                print(participant.on_message(sender_id, message))


@dataclass(slots=True)
class UserParticipant(Participant):
    def on_message(self, from_id: str, message: str) -> str:
        return _format_incoming_message(self.id, from_id, message)


def _format_incoming_message(receiver_id: str, sender_id: str, message: str) -> str:
    return f"{USER_PREFIX}{receiver_id}{RECEIVED_INFIX}{sender_id}{MESSAGE_SEPARATOR}{message}"
