from __future__ import annotations

from dataclasses import dataclass
from typing import Dict, Tuple


@dataclass(frozen=True, slots=True)
class TextStyle:
    font_family: str
    font_size: int
    color_hex: str
    bold: bool
    italic: bool


@dataclass(slots=True)
class StyledText:
    text: str
    style: TextStyle

    def render(self) -> str:
        return _render_style(self.style, self.text)


class TextStyleFactory:
    def __init__(self) -> None:
        self._cache: Dict[Tuple[str, int, str, bool, bool], TextStyle] = {}

    def get_style(
        self,
        font_family: str,
        font_size: int,
        color_hex: str,
        bold: bool,
        italic: bool,
    ) -> TextStyle:
        key = (font_family, font_size, color_hex, bold, italic)
        if key not in self._cache:
            self._cache[key] = TextStyle(*key)
        return self._cache[key]

    def cache_size(self) -> int:
        return len(self._cache)


def _render_style(style: TextStyle, text: str) -> str:
    return (
        f"[{style.font_family},{style.font_size},{style.color_hex},"
        f"bold={style.bold},italic={style.italic}] {text}"
    )
