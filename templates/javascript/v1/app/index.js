import { profile } from "../decorator/perf.js";
import { ShapeFactory } from "../factory/shapeFactory.js";
import { GreetingFacade } from "../facade/greetingFacade.js";
import { getTextStyle, StyledText, cacheSize } from "../flyweight/textStyleFactory.js";
import { createTimedProxy } from "../proxy/timedProxy.js";
import { ElementNode, TextNode } from "../composite/nodes.js";
import { textFormatter, jsonFormatter } from "../bridge/formatters.js";
import { EmailNotifier, SmsNotifier } from "../bridge/notifiers.js";
import { LegacyLogger } from "../adapter/legacyLogger.js";
import { LegacyLoggerAdapter } from "../adapter/loggerAdapter.js";
import { HttpRequestBuilder } from "../builder/httpRequestBuilder.js";
import { DocumentPrototype } from "../prototype/documentPrototype.js";
import { appConfig } from "../singleton/appConfig.js";
import {
  ValidationHandler,
  AuthHandler,
  BusinessHandler
} from "../chain/handlers.js";
import {
  TextBuffer,
  AppendTextCommand,
  CommandInvoker
} from "../command/commands.js";
import { ItemCollection } from "../iterator/collection.js";
import { ChatMediator, UserParticipant } from "../mediator/chatMediator.js";
import { TextEditor } from "../memento/textEditor.js";
import { EventBus, LoggingObserver } from "../observer/eventBus.js";
import { DocumentContext, draftState, publishedState } from "../state/states.js";
import {
  SummaryReportGenerator,
  DetailedReportGenerator
} from "../templatemethod/reportGenerators.js";
import { ElementNode, TextNode, RenderVisitor } from "../visitor/nodes.js";
import {
  EmailNotificationCreator,
  SmsNotificationCreator
} from "../factorymethod/notifications.js";
import { LightUiFactory, DarkUiFactory } from "../abstractfactory/uiFactory.js";
import {
  friendlyStrategy,
  formalStrategy,
  uppercaseStrategy,
  GreeterContext
} from "../strategy/greeterStrategies.js";

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

const context = new GreeterContext(friendlyStrategy);
console.log(context.greet("{{projectName}}"));

context.setStrategy(formalStrategy);
console.log(context.greet("{{projectName}}"));

context.setStrategy(uppercaseStrategy);
console.log(context.greet("{{projectName}}"));

const facade = new GreetingFacade();
const result = facade.greetAndDraw("{{projectName}}", formalStrategy, "square");
console.log(result.greeting);
console.log(result.drawing);

const headline = getTextStyle("Inter", 18, "#111111", true, false);
const body = getTextStyle("Inter", 14, "#333333", false, false);
const headlineShared = getTextStyle("Inter", 18, "#111111", true, false);

const line1 = new StyledText("Flyweight headline", headline);
const line2 = new StyledText("Flyweight body", body);
const line3 = new StyledText("Headline reused", headlineShared);

console.log(line1.render());
console.log(line2.render());
console.log(line3.render());
console.log(`Flyweight cache size: ${cacheSize()}`);

class GreeterService {
  greet(name) {
    return `Hello ${name} from proxy`;
  }
}

const greeter = new GreeterService();
const timedGreeter = createTimedProxy(greeter);
console.log(timedGreeter.greet("{{projectName}}"));

const root = new ElementNode("div")
  .add(new TextNode("Composite root: "))
  .add(new ElementNode("span").add(new TextNode("child")));
console.log(root.render());

const emailText = new EmailNotifier(textFormatter);
const emailJson = new EmailNotifier(jsonFormatter);
const smsText = new SmsNotifier(textFormatter);

console.log(emailText.notify("Bridge message"));
console.log(emailJson.notify("Bridge message"));
console.log(smsText.notify("Bridge message"));

const logger = new LegacyLoggerAdapter(new LegacyLogger());
logger.log("INFO", "Adapter message");

const request = new HttpRequestBuilder()
  .setUrl("https://api.example.com/messages")
  .setMethod("POST")
  .addHeader("Content-Type", "application/json")
  .addHeader("Accept", "application/json")
  .setBody("{\"message\":\"Hello\"}")
  .setTimeoutMs(3000)
  .build();
console.log(request.summary());

const baseDoc = new DocumentPrototype("Prototype", ["Intro"]);
const copy = baseDoc.clonePrototype();
copy.setTitle("Prototype Copy");
copy.addParagraph("Second paragraph");
console.log(baseDoc.summary());
console.log(copy.summary());

appConfig.set("env", "dev");
console.log(`Singleton env: ${appConfig.get("env", "local")}`);

const validation = new ValidationHandler();
const auth = new AuthHandler();
const business = new BusinessHandler();
validation.linkWith(auth).linkWith(business);

const request = { id: "req-1", payload: "token:abc; payload data" };
console.log(validation.handle(request));

const buffer = new TextBuffer();
const invoker = new CommandInvoker();
console.log(invoker.run(new AppendTextCommand(buffer, "Hello")));
console.log(invoker.run(new AppendTextCommand(buffer, " Command")));
console.log(`Command history size: ${invoker.historySize()}`);

const collection = new ItemCollection();
collection.add({ id: "1", name: "Alpha" });
collection.add({ id: "2", name: "Beta" });
for (const item of collection) {
  console.log(`Iterator item: ${item.id}=${item.name}`);
}

const room = new ChatMediator();
const alice = new UserParticipant("alice", room);
const bob = new UserParticipant("bob", room);
room.register(alice);
room.register(bob);
alice.send("Mediator hello");

const editor = new TextEditor();
editor.append("Hello");
const snapshot = editor.save();
editor.append(" Memento");
console.log(`Memento current: ${editor.current()}`);
editor.restore(snapshot);
console.log(`Memento restored: ${editor.current()}`);

const bus = new EventBus();
const obsA = new LoggingObserver("A");
const obsB = new LoggingObserver("B");
bus.subscribe(obsA);
bus.subscribe(obsB);
bus.publish("message.sent", "Observer hello");

const doc = new DocumentContext(draftState);
console.log(`State ${doc.currentState()}: ${doc.process("State message")}`);
doc.setState(publishedState);
console.log(`State ${doc.currentState()}: ${doc.process("State message")}`);

const summary = new SummaryReportGenerator();
const detailed = new DetailedReportGenerator();
console.log(summary.generate("Template Method report"));
console.log(detailed.generate("Line one\nLine two"));

const root = new ElementNode("div")
  .add(new TextNode("Visitor root: "))
  .add(new ElementNode("span").add(new TextNode("child")));
const renderer = new RenderVisitor();
console.log(root.accept(renderer));

const emailCreator = new EmailNotificationCreator();
const smsCreator = new SmsNotificationCreator();
console.log(emailCreator.notify("Factory Method message"));
console.log(smsCreator.notify("Factory Method message"));

const light = new LightUiFactory();
const dark = new DarkUiFactory();
console.log(`${light.createButton().render()},${light.createCheckbox().render()}`);
console.log(`${dark.createButton().render()},${dark.createCheckbox().render()}`);