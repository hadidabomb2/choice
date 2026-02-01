from __future__ import annotations

from dataclasses import dataclass
from typing import Protocol

LIGHT_BUTTON_LABEL = "LightButton"
LIGHT_CHECKBOX_LABEL = "LightCheckbox"
DARK_BUTTON_LABEL = "DarkButton"
DARK_CHECKBOX_LABEL = "DarkCheckbox"


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
        return LIGHT_BUTTON_LABEL


@dataclass(slots=True)
class LightCheckbox:
    def render(self) -> str:
        return LIGHT_CHECKBOX_LABEL


@dataclass(slots=True)
class DarkButton:
    def render(self) -> str:
        return DARK_BUTTON_LABEL


@dataclass(slots=True)
class DarkCheckbox:
    def render(self) -> str:
        return DARK_CHECKBOX_LABEL


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
