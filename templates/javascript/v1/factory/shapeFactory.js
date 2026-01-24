const NOT_IMPLEMENTED_ERROR = "Not implemented";
const SHAPE_CIRCLE = "circle";
const SHAPE_SQUARE = "square";
const UNKNOWN_SHAPE_PREFIX = "Unknown shape: ";

class Shape {
  draw() {
    throw new Error(NOT_IMPLEMENTED_ERROR);
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

const SHAPE_BUILDERS = new Map([
  [SHAPE_CIRCLE, () => new Circle()],
  [SHAPE_SQUARE, () => new Square()]
]);

export class ShapeFactory {
  static create(kind) {
    const normalizedKind = kind.toLowerCase();
    const factory = SHAPE_BUILDERS.get(normalizedKind);
    if (!factory) {
      throw new Error(`${UNKNOWN_SHAPE_PREFIX}${kind}`);
    }
    return factory();
  }
}