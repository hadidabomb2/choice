import { ShapeFactory } from "../factory/shapeFactory.js";
import { GreeterContext } from "../strategy/greeterStrategies.js";

export class GreetingFacade {
  greetAndDraw(name, strategy, shapeKind) {
    const context = new GreeterContext(strategy);
    const greeting = context.greet(name);

    const shape = ShapeFactory.create(shapeKind);
    const drawing = shape.draw();

    return { greeting, drawing };
  }
}
