import { profile } from "../decorator/perf.js";
import { ShapeFactory } from "../factory/shapeFactory.js";

class Greeter {
  hello(name) {
    const message = `Hello world from ${name}`;
    return message;
  }
}

const g = new Greeter();
g.hello = profile("Greeter.hello", g.hello);

console.log(g.hello("{{projectName}}"));

const shape = ShapeFactory.create("circle");
console.log(shape.draw());