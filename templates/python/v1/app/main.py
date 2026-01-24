from decorator.perf import profile
from factory.shape_factory import ShapeFactory


class Greeter:
    @profile()
    def hello(self, name: str) -> str:
        message = f"Hello world from {name}"
        return message


def main() -> None:
    g = Greeter()
    print(g.hello("{{projectName}}"))

    shape = ShapeFactory.create("circle")
    print(shape.draw())


if __name__ == "__main__":
    main()