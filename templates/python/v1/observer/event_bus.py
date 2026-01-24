from __future__ import annotations

from dataclasses import dataclass, field
from typing import Protocol, Set


class Observer(Protocol):
    def update(self, event: str, payload: str) -> None:
        ...


@dataclass(slots=True)
class EventBus:
    observers: Set[Observer] = field(default_factory=set)

    def subscribe(self, observer: Observer) -> None:
        self.observers.add(observer)

    def unsubscribe(self, observer: Observer) -> None:
        self.observers.discard(observer)

    def publish(self, event: str, payload: str) -> None:
        for observer in self.observers:
            observer.update(event, payload)


@dataclass(slots=True)
class LoggingObserver:
    name: str

    def update(self, event: str, payload: str) -> None:
        print(f"Observer {self.name} saw {event}: {payload}")
