package facade;

import factory.Shape;
import factory.ShapeFactory;
import strategy.GreeterContext;
import strategy.GreeterStrategy;

public class GreetingFacade {
    public FacadeResult greetAndDraw(String name, GreeterStrategy strategy, String shapeKind) {
        GreeterContext context = new GreeterContext(strategy);
        String greeting = context.greet(name);

        Shape shape = ShapeFactory.create(shapeKind);
        String drawing = shape.draw();

        return new FacadeResult(greeting, drawing);
    }
}
