import { profile } from "../decorator/perf.js";
import { ShapeFactory } from "../factory/shapeFactory.js";
import { GreetingFacade } from "../facade/greetingFacade.js";
import {
  getTextStyle,
  StyledText,
  cacheSize
} from "../flyweight/textStyleFactory.js";
import { createTimedProxy } from "../proxy/timedProxy.js";
import {
  ElementNode as CompositeElementNode,
  TextNode as CompositeTextNode
} from "../composite/nodes.js";
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
import {
  ElementNode as VisitorElementNode,
  TextNode as VisitorTextNode,
  RenderVisitor
} from "../visitor/nodes.js";
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

const PROJECT_NAME = "{{projectName}}";
const SHAPE_CIRCLE = "circle";
const SHAPE_SQUARE = "square";
const FONT_FAMILY = "Inter";
const HEADLINE_FONT_SIZE = 18;
const BODY_FONT_SIZE = 14;
const HEADLINE_COLOR = "#111111";
const BODY_COLOR = "#333333";
const BRIDGE_MESSAGE = "Bridge message";
const ADAPTER_MESSAGE = "Adapter message";
const API_URL = "https://api.example.com/messages";
const JSON_MEDIA_TYPE = "application/json";
const JSON_BODY = "{\"message\":\"Hello\"}";
const HTTP_TIMEOUT_MS = 3000;
const PROTOTYPE_TITLE = "Prototype";
const PROTOTYPE_COPY_TITLE = "Prototype Copy";
const ENV_KEY = "env";
const ENV_VALUE = "dev";
const ENV_DEFAULT = "local";
const REQUEST_ID = "req-1";
const REQUEST_PAYLOAD = "token:abc; payload data";
const HELLO_TEXT = "Hello";
const COMMAND_APPEND = " Command";
const ITERATOR_ITEM_ONE_ID = "1";
const ITERATOR_ITEM_ONE_NAME = "Alpha";
const ITERATOR_ITEM_TWO_ID = "2";
const ITERATOR_ITEM_TWO_NAME = "Beta";
const MEDIATOR_USER_ALICE = "alice";
const MEDIATOR_USER_BOB = "bob";
const MEDIATOR_MESSAGE = "Mediator hello";
const MEMENTO_APPEND = " Memento";
const OBSERVER_EVENT = "message.sent";
const OBSERVER_MESSAGE = "Observer hello";
const STATE_MESSAGE = "State message";
const TEMPLATE_METHOD_TITLE = "Template Method report";
const TEMPLATE_METHOD_DETAILS = "Line one\nLine two";
const FACTORY_METHOD_MESSAGE = "Factory Method message";

class Greeter {
  hello(name) {
    return `Hello world from ${name}`;
  }
}

class GreeterService {
  greet(name) {
    return `Hello ${name} from proxy`;
  }
}

function runDecoratorExample() {
  const greeter = new Greeter();
  greeter.hello = profile("Greeter.hello", greeter.hello);
  console.log(greeter.hello(PROJECT_NAME));
}

function runSimpleFactoryExample() {
  const shape = ShapeFactory.create(SHAPE_CIRCLE);
  console.log(shape.draw());
}

function runStrategyExample() {
  const context = new GreeterContext(friendlyStrategy);
  console.log(context.greet(PROJECT_NAME));

  context.setStrategy(formalStrategy);
  console.log(context.greet(PROJECT_NAME));

  context.setStrategy(uppercaseStrategy);
  console.log(context.greet(PROJECT_NAME));
}

function runFacadeExample() {
  const facade = new GreetingFacade();
  const facadeResult = facade.greetAndDraw(PROJECT_NAME, formalStrategy, SHAPE_SQUARE);
  console.log(facadeResult.greeting);
  console.log(facadeResult.drawing);
}

function runFlyweightExample() {
  const headline = getTextStyle(
    FONT_FAMILY,
    HEADLINE_FONT_SIZE,
    HEADLINE_COLOR,
    true,
    false
  );
  const body = getTextStyle(FONT_FAMILY, BODY_FONT_SIZE, BODY_COLOR, false, false);
  const headlineShared = getTextStyle(
    FONT_FAMILY,
    HEADLINE_FONT_SIZE,
    HEADLINE_COLOR,
    true,
    false
  );

  const line1 = new StyledText("Flyweight headline", headline);
  const line2 = new StyledText("Flyweight body", body);
  const line3 = new StyledText("Headline reused", headlineShared);

  console.log(line1.render());
  console.log(line2.render());
  console.log(line3.render());
  console.log(`Flyweight cache size: ${cacheSize()}`);
}

function runProxyExample() {
  const greeter = new GreeterService();
  const timedGreeter = createTimedProxy(greeter);
  console.log(timedGreeter.greet(PROJECT_NAME));
}

function runCompositeExample() {
  const compositeRoot = new CompositeElementNode("div")
    .add(new CompositeTextNode("Composite root: "))
    .add(new CompositeElementNode("span").add(new CompositeTextNode("child")));
  console.log(compositeRoot.render());
}

function runBridgeExample() {
  const emailText = new EmailNotifier(textFormatter);
  const emailJson = new EmailNotifier(jsonFormatter);
  const smsText = new SmsNotifier(textFormatter);

  console.log(emailText.notify(BRIDGE_MESSAGE));
  console.log(emailJson.notify(BRIDGE_MESSAGE));
  console.log(smsText.notify(BRIDGE_MESSAGE));
}

function runAdapterExample() {
  const logger = new LegacyLoggerAdapter(new LegacyLogger());
  logger.log("INFO", ADAPTER_MESSAGE);
}

function runBuilderExample() {
  const httpRequest = new HttpRequestBuilder()
    .setUrl(API_URL)
    .setMethod("POST")
    .addHeader("Content-Type", JSON_MEDIA_TYPE)
    .addHeader("Accept", JSON_MEDIA_TYPE)
    .setBody(JSON_BODY)
    .setTimeoutMs(HTTP_TIMEOUT_MS)
    .build();
  console.log(httpRequest.summary());
}

function runPrototypeExample() {
  const baseDoc = new DocumentPrototype(PROTOTYPE_TITLE, ["Intro"]);
  const copy = baseDoc.clonePrototype();
  copy.setTitle(PROTOTYPE_COPY_TITLE);
  copy.addParagraph("Second paragraph");
  console.log(baseDoc.summary());
  console.log(copy.summary());
}

function runSingletonExample() {
  appConfig.set(ENV_KEY, ENV_VALUE);
  console.log(`Singleton env: ${appConfig.get(ENV_KEY, ENV_DEFAULT)}`);
}

function runChainExample() {
  const validation = new ValidationHandler();
  const auth = new AuthHandler();
  const business = new BusinessHandler();
  validation.linkWith(auth).linkWith(business);

  const workflowRequest = { id: REQUEST_ID, payload: REQUEST_PAYLOAD };
  console.log(validation.handle(workflowRequest));
}

function runCommandExample() {
  const buffer = new TextBuffer();
  const invoker = new CommandInvoker();
  console.log(invoker.run(new AppendTextCommand(buffer, HELLO_TEXT)));
  console.log(invoker.run(new AppendTextCommand(buffer, COMMAND_APPEND)));
  console.log(`Command history size: ${invoker.historySize()}`);
}

function runIteratorExample() {
  const collection = new ItemCollection();
  collection.add({ id: ITERATOR_ITEM_ONE_ID, name: ITERATOR_ITEM_ONE_NAME });
  collection.add({ id: ITERATOR_ITEM_TWO_ID, name: ITERATOR_ITEM_TWO_NAME });
  for (const item of collection) {
    console.log(`Iterator item: ${item.id}=${item.name}`);
  }
}

function runMediatorExample() {
  const room = new ChatMediator();
  const alice = new UserParticipant(MEDIATOR_USER_ALICE, room);
  const bob = new UserParticipant(MEDIATOR_USER_BOB, room);
  room.register(alice);
  room.register(bob);
  alice.send(MEDIATOR_MESSAGE);
}

function runMementoExample() {
  const editor = new TextEditor();
  editor.append(HELLO_TEXT);
  const snapshot = editor.save();
  editor.append(MEMENTO_APPEND);
  console.log(`Memento current: ${editor.current()}`);
  editor.restore(snapshot);
  console.log(`Memento restored: ${editor.current()}`);
}

function runObserverExample() {
  const bus = new EventBus();
  const obsA = new LoggingObserver("A");
  const obsB = new LoggingObserver("B");
  bus.subscribe(obsA);
  bus.subscribe(obsB);
  bus.publish(OBSERVER_EVENT, OBSERVER_MESSAGE);
}

function runStateExample() {
  const doc = new DocumentContext(draftState);
  console.log(`State ${doc.currentState()}: ${doc.process(STATE_MESSAGE)}`);
  doc.setState(publishedState);
  console.log(`State ${doc.currentState()}: ${doc.process(STATE_MESSAGE)}`);
}

function runTemplateMethodExample() {
  const summary = new SummaryReportGenerator();
  const detailed = new DetailedReportGenerator();
  console.log(summary.generate(TEMPLATE_METHOD_TITLE));
  console.log(detailed.generate(TEMPLATE_METHOD_DETAILS));
}

function runVisitorExample() {
  const visitorRoot = new VisitorElementNode("div")
    .add(new VisitorTextNode("Visitor root: "))
    .add(new VisitorElementNode("span").add(new VisitorTextNode("child")));
  const renderer = new RenderVisitor();
  console.log(visitorRoot.accept(renderer));
}

function runFactoryMethodExample() {
  const emailCreator = new EmailNotificationCreator();
  const smsCreator = new SmsNotificationCreator();
  console.log(emailCreator.notify(FACTORY_METHOD_MESSAGE));
  console.log(smsCreator.notify(FACTORY_METHOD_MESSAGE));
}

function runAbstractFactoryExample() {
  const light = new LightUiFactory();
  const dark = new DarkUiFactory();
  console.log(`${light.createButton().render()},${light.createCheckbox().render()}`);
  console.log(`${dark.createButton().render()},${dark.createCheckbox().render()}`);
}

function runAllExamples() {
  runDecoratorExample();
  runSimpleFactoryExample();
  runStrategyExample();
  runFacadeExample();
  runFlyweightExample();
  runProxyExample();
  runCompositeExample();
  runBridgeExample();
  runAdapterExample();
  runBuilderExample();
  runPrototypeExample();
  runSingletonExample();
  runChainExample();
  runCommandExample();
  runIteratorExample();
  runMediatorExample();
  runMementoExample();
  runObserverExample();
  runStateExample();
  runTemplateMethodExample();
  runVisitorExample();
  runFactoryMethodExample();
  runAbstractFactoryExample();
}

runAllExamples();