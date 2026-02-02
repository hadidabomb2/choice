package designtemplates;

import designtemplates.decorator.Perf;
import designtemplates.decorator.PerfTracker;
import designtemplates.factory.Shape;
import designtemplates.factory.ShapeFactory;
import designtemplates.facade.FacadeResult;
import designtemplates.facade.GreetingFacade;
import designtemplates.flyweight.StyledText;
import designtemplates.flyweight.TextStyle;
import designtemplates.flyweight.TextStyleFactory;
import designtemplates.composite.ElementNode;
import designtemplates.composite.TextNode;
import designtemplates.bridge.EmailNotifier;
import designtemplates.bridge.JsonFormatter;
import designtemplates.bridge.SmsNotifier;
import designtemplates.bridge.TextFormatter;
import designtemplates.adapter.LegacyLogger;
import designtemplates.adapter.LegacyLoggerAdapter;
import designtemplates.adapter.Logger;
import designtemplates.builder.HttpRequest;
import designtemplates.prototype.Document;
import designtemplates.singleton.AppConfig;
import designtemplates.chain.AuthHandler;
import designtemplates.chain.BusinessHandler;
import designtemplates.chain.Request;
import designtemplates.chain.ValidationHandler;
import designtemplates.command.AppendTextCommand;
import designtemplates.command.CommandInvoker;
import designtemplates.command.TextBuffer;
import designtemplates.iterator.Item;
import designtemplates.iterator.ItemCollection;
import designtemplates.mediator.ChatRoomMediator;
import designtemplates.mediator.UserParticipant;
import designtemplates.memento.TextEditor;
import designtemplates.memento.TextSnapshot;
import designtemplates.observer.EventBus;
import designtemplates.observer.LoggingObserver;
import designtemplates.state.DocumentContext;
import designtemplates.state.DraftState;
import designtemplates.state.PublishedState;
import designtemplates.templatemethod.DetailedReportGenerator;
import designtemplates.templatemethod.SummaryReportGenerator;
import designtemplates.visitor.RenderVisitor;
import designtemplates.factorymethod.EmailNotificationCreator;
import designtemplates.factorymethod.SmsNotificationCreator;
import designtemplates.abstractfactory.DarkUiFactory;
import designtemplates.abstractfactory.LightUiFactory;
import designtemplates.abstractfactory.UiFactory;
import designtemplates.proxy.GreeterProxyFactory;
import designtemplates.proxy.GreeterService;
import designtemplates.proxy.GreeterServiceImpl;
import designtemplates.strategy.FormalStrategy;
import designtemplates.strategy.FriendlyStrategy;
import designtemplates.strategy.GreeterContext;
import designtemplates.strategy.UppercaseStrategy;

import java.lang.reflect.Method;

public class Main {
    private static final String PROJECT_NAME = "{{projectName}}";
    private static final String SHAPE_CIRCLE = "circle";
    private static final String SHAPE_SQUARE = "square";
    private static final String FONT_FAMILY = "Inter";
    private static final int HEADLINE_FONT_SIZE = 18;
    private static final int BODY_FONT_SIZE = 14;
    private static final String HEADLINE_COLOR = "#111111";
    private static final String BODY_COLOR = "#333333";
    private static final String BRIDGE_MESSAGE = "Bridge message";
    private static final String ADAPTER_MESSAGE = "Adapter message";
    private static final String API_URL = "https://api.example.com/messages";
    private static final String JSON_MEDIA_TYPE = "application/json";
    private static final String JSON_BODY = "{\"message\":\"Hello\"}";
    private static final int HTTP_TIMEOUT_MS = 3000;
    private static final String PROTOTYPE_TITLE = "Prototype";
    private static final String PROTOTYPE_COPY_TITLE = "Prototype Copy";
    private static final String ENV_KEY = "env";
    private static final String ENV_VALUE = "dev";
    private static final String ENV_DEFAULT = "local";
    private static final String REQUEST_ID = "req-1";
    private static final String REQUEST_PAYLOAD = "token:abc; payload data";
    private static final String HELLO_TEXT = "Hello";
    private static final String COMMAND_APPEND = " Command";
    private static final String ITERATOR_ITEM_ONE_ID = "1";
    private static final String ITERATOR_ITEM_ONE_NAME = "Alpha";
    private static final String ITERATOR_ITEM_TWO_ID = "2";
    private static final String ITERATOR_ITEM_TWO_NAME = "Beta";
    private static final String MEDIATOR_USER_ALICE = "alice";
    private static final String MEDIATOR_USER_BOB = "bob";
    private static final String MEDIATOR_MESSAGE = "Mediator hello";
    private static final String MEMENTO_APPEND = " Memento";
    private static final String OBSERVER_EVENT = "message.sent";
    private static final String OBSERVER_MESSAGE = "Observer hello";
    private static final String STATE_MESSAGE = "State message";
    private static final String TEMPLATE_METHOD_TITLE = "Template Method report";
    private static final String TEMPLATE_METHOD_DETAILS = "Line one\nLine two";
    private static final String FACTORY_METHOD_MESSAGE = "Factory Method message";

    static class Greeter {
        @Perf(name = "Greeter.hello")
        public String hello(String name) {
            return "Hello world from " + name;
        }
    }

    public static void main(String[] args) throws Exception {
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

    private static void runDecoratorExample() throws Exception {
        Greeter greeter = new Greeter();
        Method method = Greeter.class.getMethod("hello", String.class);
        String message = (String) PerfTracker.invoke(greeter, method, PROJECT_NAME);
        System.out.println(message);
    }

    private static void runSimpleFactoryExample() {
        Shape shape = ShapeFactory.create(SHAPE_CIRCLE);
        System.out.println(shape.draw());
    }

    private static void runStrategyExample() {
        GreeterContext context = new GreeterContext(new FriendlyStrategy());
        System.out.println(context.greet(PROJECT_NAME));

        context.setStrategy(new FormalStrategy());
        System.out.println(context.greet(PROJECT_NAME));

        context.setStrategy(new UppercaseStrategy());
        System.out.println(context.greet(PROJECT_NAME));
    }

    private static void runFacadeExample() {
        GreetingFacade facade = new GreetingFacade();
        FacadeResult facadeResult = facade.greetAndDraw(PROJECT_NAME, new FormalStrategy(), SHAPE_SQUARE);
        System.out.println(facadeResult.getGreeting());
        System.out.println(facadeResult.getDrawing());
    }

    private static void runFlyweightExample() {
        TextStyleFactory styleFactory = new TextStyleFactory();
        TextStyle headline = styleFactory.getStyle(FONT_FAMILY, HEADLINE_FONT_SIZE, HEADLINE_COLOR, true, false);
        TextStyle body = styleFactory.getStyle(FONT_FAMILY, BODY_FONT_SIZE, BODY_COLOR, false, false);
        TextStyle headlineShared = styleFactory.getStyle(FONT_FAMILY, HEADLINE_FONT_SIZE, HEADLINE_COLOR, true, false);

        StyledText line1 = new StyledText("Flyweight headline", headline);
        StyledText line2 = new StyledText("Flyweight body", body);
        StyledText line3 = new StyledText("Headline reused", headlineShared);

        System.out.println(line1.render());
        System.out.println(line2.render());
        System.out.println(line3.render());
        System.out.println("Flyweight cache size: " + styleFactory.cacheSize());
    }

    private static void runProxyExample() {
        GreeterService service = new GreeterServiceImpl();
        GreeterService timedService = GreeterProxyFactory.createTimed(service);
        System.out.println(timedService.greet(PROJECT_NAME));
    }

    private static void runCompositeExample() {
        ElementNode compositeRoot = new ElementNode("div")
            .add(new TextNode("Composite root: "))
            .add(new ElementNode("span")
                .add(new TextNode("child")));
        System.out.println(compositeRoot.render());
    }

    private static void runBridgeExample() {
        EmailNotifier emailText = new EmailNotifier(new TextFormatter());
        EmailNotifier emailJson = new EmailNotifier(new JsonFormatter());
        SmsNotifier smsText = new SmsNotifier(new TextFormatter());

        System.out.println(emailText.notify(BRIDGE_MESSAGE));
        System.out.println(emailJson.notify(BRIDGE_MESSAGE));
        System.out.println(smsText.notify(BRIDGE_MESSAGE));
    }

    private static void runAdapterExample() {
        Logger logger = new LegacyLoggerAdapter(new LegacyLogger());
        logger.log("INFO", ADAPTER_MESSAGE);
    }

    private static void runBuilderExample() {
        HttpRequest httpRequest = new HttpRequest.Builder()
            .url(API_URL)
            .method("POST")
            .header("Content-Type", JSON_MEDIA_TYPE)
            .header("Accept", JSON_MEDIA_TYPE)
            .body(JSON_BODY)
            .timeoutMs(HTTP_TIMEOUT_MS)
            .build();
        System.out.println(httpRequest.summary());
    }

    private static void runPrototypeExample() {
        Document baseDoc = new Document(PROTOTYPE_TITLE, java.util.List.of("Intro"));
        Document copy = baseDoc.clonePrototype();
        copy.setTitle(PROTOTYPE_COPY_TITLE);
        copy.addParagraph("Second paragraph");
        System.out.println(baseDoc.summary());
        System.out.println(copy.summary());
    }

    private static void runSingletonExample() {
        AppConfig config = AppConfig.getInstance();
        config.set(ENV_KEY, ENV_VALUE);
        System.out.println("Singleton env: " + config.get(ENV_KEY, ENV_DEFAULT));
    }

    private static void runChainExample() {
        ValidationHandler validation = new ValidationHandler();
        AuthHandler auth = new AuthHandler();
        BusinessHandler business = new BusinessHandler();
        validation.linkWith(auth).linkWith(business);

        Request workflowRequest = new Request(REQUEST_ID, REQUEST_PAYLOAD);
        System.out.println(validation.handle(workflowRequest));
    }

    private static void runCommandExample() {
        TextBuffer buffer = new TextBuffer();
        CommandInvoker invoker = new CommandInvoker();
        System.out.println(invoker.run(new AppendTextCommand(buffer, HELLO_TEXT)));
        System.out.println(invoker.run(new AppendTextCommand(buffer, COMMAND_APPEND)));
        System.out.println("Command history size: " + invoker.historySize());
    }

    private static void runIteratorExample() {
        ItemCollection collection = new ItemCollection();
        collection.add(new Item(ITERATOR_ITEM_ONE_ID, ITERATOR_ITEM_ONE_NAME));
        collection.add(new Item(ITERATOR_ITEM_TWO_ID, ITERATOR_ITEM_TWO_NAME));
        for (Item item : collection) {
            System.out.println("Iterator item: " + item.getId() + "=" + item.getName());
        }
    }

    private static void runMediatorExample() {
        ChatRoomMediator room = new ChatRoomMediator();
        UserParticipant alice = new UserParticipant(MEDIATOR_USER_ALICE, room);
        UserParticipant bob = new UserParticipant(MEDIATOR_USER_BOB, room);
        room.register(alice);
        room.register(bob);
        alice.send(MEDIATOR_MESSAGE);
    }

    private static void runMementoExample() {
        TextEditor editor = new TextEditor();
        editor.append(HELLO_TEXT);
        TextSnapshot snapshot = editor.save();
        editor.append(MEMENTO_APPEND);
        System.out.println("Memento current: " + editor.current());
        editor.restore(snapshot);
        System.out.println("Memento restored: " + editor.current());
    }

    private static void runObserverExample() {
        EventBus bus = new EventBus();
        LoggingObserver obsA = new LoggingObserver("A");
        LoggingObserver obsB = new LoggingObserver("B");
        bus.subscribe(obsA);
        bus.subscribe(obsB);
        bus.publish(OBSERVER_EVENT, OBSERVER_MESSAGE);
    }

    private static void runStateExample() {
        DocumentContext doc = new DocumentContext(new DraftState());
        System.out.println("State " + doc.currentState() + ": " + doc.process(STATE_MESSAGE));
        doc.setState(new PublishedState());
        System.out.println("State " + doc.currentState() + ": " + doc.process(STATE_MESSAGE));
    }

    private static void runTemplateMethodExample() {
        SummaryReportGenerator summary = new SummaryReportGenerator();
        DetailedReportGenerator detailed = new DetailedReportGenerator();
        System.out.println(summary.generate(TEMPLATE_METHOD_TITLE));
        System.out.println(detailed.generate(TEMPLATE_METHOD_DETAILS));
    }

    private static void runVisitorExample() {
        designtemplates.visitor.ElementNode visitorRoot = new designtemplates.visitor.ElementNode("div")
            .add(new designtemplates.visitor.TextNode("Visitor root: "))
            .add(new designtemplates.visitor.ElementNode("span").add(new designtemplates.visitor.TextNode("child")));
        RenderVisitor renderer = new RenderVisitor();
        System.out.println(visitorRoot.accept(renderer));
    }

    private static void runFactoryMethodExample() {
        EmailNotificationCreator emailCreator = new EmailNotificationCreator();
        SmsNotificationCreator smsCreator = new SmsNotificationCreator();
        System.out.println(emailCreator.notify(FACTORY_METHOD_MESSAGE));
        System.out.println(smsCreator.notify(FACTORY_METHOD_MESSAGE));
    }

    private static void runAbstractFactoryExample() {
        UiFactory light = new LightUiFactory();
        UiFactory dark = new DarkUiFactory();
        System.out.println(light.createButton().render() + "," + light.createCheckbox().render());
        System.out.println(dark.createButton().render() + "," + dark.createCheckbox().render());
    }
}
