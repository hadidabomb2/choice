from __future__ import annotations

from dataclasses import dataclass
from typing import Protocol


class Button(Protocol):
    def render(self) -> str:
        ...


class Checkbox(Protocol):
    def render(self) -> str:
        ...


class UiFactory(Protocol):
    def create_button(self) -> Button:
        ...

    def create_checkbox(self) -> Checkbox:
        ...


@dataclass(slots=True)
class LightButton:
    def render(self) -> str:
        return "LightButton"


@dataclass(slots=True)
class LightCheckbox:
    def render(self) -> str:
        return "LightCheckbox"


@dataclass(slots=True)
class DarkButton:
    def render(self) -> str:
        return "DarkButton"


@dataclass(slots=True)
class DarkCheckbox:
    def render(self) -> str:
        return "DarkCheckbox"


@dataclass(slots=True)
class LightUiFactory:
    def create_button(self) -> Button:
        return LightButton()

    def create_checkbox(self) -> Checkbox:
        return LightCheckbox()


@dataclass(slots=True)
class DarkUiFactory:
    def create_button(self) -> Button:
        return DarkButton()

    def create_checkbox(self) -> Checkbox:
        return DarkCheckbox()
