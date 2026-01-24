from perf import profile
from shape_factory import ShapeFactory

class Greeter:
    @profile()
    def hello(self, name: str) -> str:
        message = f"Hello world from {name}"
        return message

if __name__ == "__main__":
    g = Greeter()
    print(g.hello("{{projectName}}"))

    shape = ShapeFactory.create("circle")
    print(shape.draw())