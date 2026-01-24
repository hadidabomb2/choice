from __future__ import annotations

from dataclasses import dataclass

from factory.shape_factory import ShapeFactory
from strategy.greeter_strategies import GreeterContext, GreeterStrategy


@dataclass(slots=True)
class FacadeResult:
    greeting: str
    drawing: str


class GreetingFacade:
    def greet_and_draw(self, name: str, strategy: GreeterStrategy, shape_kind: str) -> FacadeResult:
        context = GreeterContext(strategy)
        greeting = context.greet(name)

        shape = ShapeFactory.create(shape_kind)
        drawing = shape.draw()

        return FacadeResult(greeting=greeting, drawing=drawing)
