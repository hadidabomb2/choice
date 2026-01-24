from abc import ABC, abstractmethod

UNKNOWN_SHAPE_PREFIX = "Unknown shape: "

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
        normalized_kind = kind.lower()
        match normalized_kind:
            case "circle":
                return Circle()
            case "square":
                return Square()
            case _:
                raise ValueError(f"{UNKNOWN_SHAPE_PREFIX}{kind}")