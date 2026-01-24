from abc import ABC, abstractmethod


class Shape(ABC):
    @abstractmethod
    def draw(self) -> str:
        raise NotImplementedError


class Circle(Shape):
    def draw(self) -> str:
        return "Drawing a Circle"


class Square(Shape):
    def draw(self) -> str:
        return "Drawing a Square"


class ShapeFactory:
    @staticmethod
    def create(kind: str) -> Shape:
        match kind.lower():
            case "circle":
                return Circle()
            case "square":
                return Square()
            case _:
                raise ValueError(f"Unknown shape: {kind}")