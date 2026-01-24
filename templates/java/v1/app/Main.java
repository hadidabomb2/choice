package app;

import decorator.Perf;
import decorator.PerfTracker;
import factory.Shape;
import factory.ShapeFactory;
import facade.FacadeResult;
import facade.GreetingFacade;
import flyweight.StyledText;
import flyweight.TextStyle;
import flyweight.TextStyleFactory;
import composite.ElementNode;
import composite.TextNode;
import bridge.EmailNotifier;
import bridge.JsonFormatter;
import bridge.SmsNotifier;
import bridge.TextFormatter;
import adapter.LegacyLogger;
import adapter.LegacyLoggerAdapter;
import adapter.Logger;
import builder.HttpRequest;
import prototype.Document;
import singleton.AppConfig;
import chain.AuthHandler;
import chain.BusinessHandler;
import chain.Request;
import chain.ValidationHandler;
import command.AppendTextCommand;
import command.CommandInvoker;
import command.TextBuffer;
import iterator.Item;
import iterator.ItemCollection;
import mediator.ChatRoomMediator;
import mediator.UserParticipant;
import memento.TextEditor;
import memento.TextSnapshot;
import observer.EventBus;
import observer.LoggingObserver;
import state.DocumentContext;
import state.DraftState;
import state.PublishedState;
import templatemethod.DetailedReportGenerator;
import templatemethod.SummaryReportGenerator;
import visitor.ElementNode;
import visitor.RenderVisitor;
import visitor.TextNode;
import factorymethod.EmailNotificationCreator;
import factorymethod.SmsNotificationCreator;
import abstractfactory.DarkUiFactory;
import abstractfactory.LightUiFactory;
import abstractfactory.UiFactory;
import proxy.GreeterProxyFactory;
import proxy.GreeterService;
import proxy.GreeterServiceImpl;
import strategy.FormalStrategy;
import strategy.FriendlyStrategy;
import strategy.GreeterContext;
import strategy.UppercaseStrategy;

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

        GreeterContext context = new GreeterContext(new FriendlyStrategy());
        System.out.println(context.greet("{{projectName}}"));

        context.setStrategy(new FormalStrategy());
        System.out.println(context.greet("{{projectName}}"));

        context.setStrategy(new UppercaseStrategy());
        System.out.println(context.greet("{{projectName}}"));

        GreetingFacade facade = new GreetingFacade();
        FacadeResult result = facade.greetAndDraw("{{projectName}}", new FormalStrategy(), "square");
        System.out.println(result.getGreeting());
        System.out.println(result.getDrawing());

        TextStyleFactory styleFactory = new TextStyleFactory();
        TextStyle headline = styleFactory.getStyle("Inter", 18, "#111111", true, false);
        TextStyle body = styleFactory.getStyle("Inter", 14, "#333333", false, false);
        TextStyle headlineShared = styleFactory.getStyle("Inter", 18, "#111111", true, false);

        StyledText line1 = new StyledText("Flyweight headline", headline);
        StyledText line2 = new StyledText("Flyweight body", body);
        StyledText line3 = new StyledText("Headline reused", headlineShared);

        System.out.println(line1.render());
        System.out.println(line2.render());
        System.out.println(line3.render());
        System.out.println("Flyweight cache size: " + styleFactory.cacheSize());

        GreeterService service = new GreeterServiceImpl();
        GreeterService timedService = GreeterProxyFactory.createTimed(service);
        System.out.println(timedService.greet("{{projectName}}"));

        ElementNode root = new ElementNode("div")
            .add(new TextNode("Composite root: "))
            .add(new ElementNode("span")
                .add(new TextNode("child")));
        System.out.println(root.render());

        EmailNotifier emailText = new EmailNotifier(new TextFormatter());
        EmailNotifier emailJson = new EmailNotifier(new JsonFormatter());
        SmsNotifier smsText = new SmsNotifier(new TextFormatter());

        System.out.println(emailText.notify("Bridge message"));
        System.out.println(emailJson.notify("Bridge message"));
        System.out.println(smsText.notify("Bridge message"));

        Logger logger = new LegacyLoggerAdapter(new LegacyLogger());
        logger.log("INFO", "Adapter message");

        HttpRequest request = new HttpRequest.Builder()
            .url("https://api.example.com/messages")
            .method("POST")
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .body("{\"message\":\"Hello\"}")
            .timeoutMs(3000)
            .build();
        System.out.println(request.summary());

        Document baseDoc = new Document("Prototype", java.util.List.of("Intro"));
        Document copy = baseDoc.clonePrototype();
        copy.setTitle("Prototype Copy");
        copy.addParagraph("Second paragraph");
        System.out.println(baseDoc.summary());
        System.out.println(copy.summary());

        AppConfig config = AppConfig.getInstance();
        config.set("env", "dev");
        System.out.println("Singleton env: " + config.get("env", "local"));

        ValidationHandler validation = new ValidationHandler();
        AuthHandler auth = new AuthHandler();
        BusinessHandler business = new BusinessHandler();
        validation.linkWith(auth).linkWith(business);

        Request request = new Request("req-1", "token:abc; payload data");
        System.out.println(validation.handle(request));

        TextBuffer buffer = new TextBuffer();
        CommandInvoker invoker = new CommandInvoker();
        System.out.println(invoker.run(new AppendTextCommand(buffer, "Hello")));
        System.out.println(invoker.run(new AppendTextCommand(buffer, " Command")));
        System.out.println("Command history size: " + invoker.historySize());

        ItemCollection collection = new ItemCollection();
        collection.add(new Item("1", "Alpha"));
        collection.add(new Item("2", "Beta"));
        for (Item item : collection) {
            System.out.println("Iterator item: " + item.getId() + "=" + item.getName());
        }

        ChatRoomMediator room = new ChatRoomMediator();
        UserParticipant alice = new UserParticipant("alice", room);
        UserParticipant bob = new UserParticipant("bob", room);
        room.register(alice);
        room.register(bob);
        alice.send("Mediator hello");

        TextEditor editor = new TextEditor();
        editor.append("Hello");
        TextSnapshot snapshot = editor.save();
        editor.append(" Memento");
        System.out.println("Memento current: " + editor.current());
        editor.restore(snapshot);
        System.out.println("Memento restored: " + editor.current());

        EventBus bus = new EventBus();
        LoggingObserver obsA = new LoggingObserver("A");
        LoggingObserver obsB = new LoggingObserver("B");
        bus.subscribe(obsA);
        bus.subscribe(obsB);
        bus.publish("message.sent", "Observer hello");

        DocumentContext doc = new DocumentContext(new DraftState());
        System.out.println("State " + doc.currentState() + ": " + doc.process("State message"));
        doc.setState(new PublishedState());
        System.out.println("State " + doc.currentState() + ": " + doc.process("State message"));

        SummaryReportGenerator summary = new SummaryReportGenerator();
        DetailedReportGenerator detailed = new DetailedReportGenerator();
        System.out.println(summary.generate("Template Method report"));
        System.out.println(detailed.generate("Line one\nLine two"));

        ElementNode root = new ElementNode("div")
            .add(new TextNode("Visitor root: "))
            .add(new ElementNode("span").add(new TextNode("child")));
        RenderVisitor renderer = new RenderVisitor();
        System.out.println(root.accept(renderer));

        EmailNotificationCreator emailCreator = new EmailNotificationCreator();
        SmsNotificationCreator smsCreator = new SmsNotificationCreator();
        System.out.println(emailCreator.notify("Factory Method message"));
        System.out.println(smsCreator.notify("Factory Method message"));

        UiFactory light = new LightUiFactory();
        UiFactory dark = new DarkUiFactory();
        System.out.println(light.createButton().render() + "," + light.createCheckbox().render());
        System.out.println(dark.createButton().render() + "," + dark.createCheckbox().render());
    }
}