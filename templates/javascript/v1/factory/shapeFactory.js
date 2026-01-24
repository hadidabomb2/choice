class Shape {
  draw() {
    throw new Error("Not implemented");
  }
}

class Circle extends Shape {
  draw() {
    return "Drawing a Circle";
  }
}

class Square extends Shape {
  draw() {
    return "Drawing a Square";
  }
}

export class ShapeFactory {
  static create(kind) {
    switch (kind.toLowerCase()) {
      case "circle":
        return new Circle();
      case "square":
        return new Square();
      default:
        throw new Error(`Unknown shape: ${kind}`);
    }
  }
}