package designtemplates.facade;

import designtemplates.factory.Shape;
import designtemplates.factory.ShapeFactory;
import designtemplates.strategy.GreeterContext;
import designtemplates.strategy.GreeterStrategy;

public class GreetingFacade {
    public FacadeResult greetAndDraw(String name, GreeterStrategy strategy, String shapeKind) {
        GreeterContext context = new GreeterContext(strategy);
        String greeting = context.greet(name);

        Shape shape = ShapeFactory.create(shapeKind);
        String drawing = shape.draw();

        return createResult(greeting, drawing);
    }

    private FacadeResult createResult(String greeting, String drawing) {
        return new FacadeResult(greeting, drawing);
    }
}
