package app;

import decorator.Perf;
import decorator.PerfTracker;
import factory.Shape;
import factory.ShapeFactory;

import java.lang.reflect.Method;

public class Main {
    static class Greeter {
        @Perf(name = "Greeter.hello")
        public String hello(String name) {
            String message = "Hello world from " + name;
            return message;
        }
    }

    public static void main(String[] args) throws Exception {
        Greeter g = new Greeter();

        Method m = Greeter.class.getMethod("hello", String.class);
        String result = (String) PerfTracker.invoke(g, m, "{{projectName}}");

        System.out.println(result);

        Shape shape = ShapeFactory.create("circle");
        System.out.println(shape.draw());
    }
}