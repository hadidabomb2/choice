from decorator.perf import profile
from factory.shape_factory import ShapeFactory
from facade.greeting_facade import GreetingFacade
from flyweight.text_style_factory import TextStyleFactory, StyledText
from composite.nodes import ElementNode, TextNode
from proxy.timed_proxy import TimedProxy
from bridge.formatters import JsonFormatter, TextFormatter
from bridge.notifiers import EmailNotifier, SmsNotifier
from adapter.legacy_logger import LegacyLogger
from adapter.logger_adapter import LegacyLoggerAdapter
from builder.http_request_builder import HttpRequestBuilder
from prototype.document_prototype import DocumentPrototype
from singleton.app_config import AppConfig
from chain.handlers import ValidationHandler, AuthHandler, BusinessHandler, Request
from command.commands import TextBuffer, AppendTextCommand, CommandInvoker
from iterator.collection import ItemCollection, Item
from mediator.chat_mediator import ChatMediator, UserParticipant
from memento.text_editor import TextEditor
from observer.event_bus import EventBus, LoggingObserver
from state.states import DocumentContext, DraftState, PublishedState
from templatemethod.report_generators import SummaryReportGenerator, DetailedReportGenerator
from visitor.nodes import ElementNode, TextNode, RenderVisitor
from factorymethod.notifications import EmailNotificationCreator, SmsNotificationCreator
from abstractfactory.ui_factory import LightUiFactory, DarkUiFactory
from strategy.greeter_strategies import (
    FriendlyStrategy,
    FormalStrategy,
    UppercaseStrategy,
    GreeterContext,
)


class Greeter:
    @profile()
    def hello(self, name: str) -> str:
        message = f"Hello world from {name}"
        return message


def main() -> None:
    g = Greeter()
    print(g.hello("{{projectName}}"))

    shape = ShapeFactory.create("circle")
    print(shape.draw())

    context = GreeterContext(FriendlyStrategy())
    print(context.greet("{{projectName}}"))

    context.set_strategy(FormalStrategy())
    print(context.greet("{{projectName}}"))

    context.set_strategy(UppercaseStrategy())
    print(context.greet("{{projectName}}"))

    facade = GreetingFacade()
    result = facade.greet_and_draw("{{projectName}}", FormalStrategy(), "square")
    print(result.greeting)
    print(result.drawing)

    style_factory = TextStyleFactory()
    headline = style_factory.get_style("Inter", 18, "#111111", True, False)
    body = style_factory.get_style("Inter", 14, "#333333", False, False)
    headline_shared = style_factory.get_style("Inter", 18, "#111111", True, False)

    line1 = StyledText("Flyweight headline", headline)
    line2 = StyledText("Flyweight body", body)
    line3 = StyledText("Headline reused", headline_shared)

    print(line1.render())
    print(line2.render())
    print(line3.render())
    print(f"Flyweight cache size: {style_factory.cache_size()}")

    class GreeterService:
        def greet(self, name: str) -> str:
            return f"Hello {name} from proxy"

    greeter = GreeterService()
    timed_greeter = TimedProxy(greeter)
    print(timed_greeter.greet("{{projectName}}"))

    root = ElementNode("div")
    root.add(TextNode("Composite root: "))
    root.add(ElementNode("span").add(TextNode("child")))
    print(root.render())

    email_text = EmailNotifier(TextFormatter())
    email_json = EmailNotifier(JsonFormatter())
    sms_text = SmsNotifier(TextFormatter())

    print(email_text.notify("Bridge message"))
    print(email_json.notify("Bridge message"))
    print(sms_text.notify("Bridge message"))

    logger = LegacyLoggerAdapter(LegacyLogger())
    logger.log("INFO", "Adapter message")

    request = (
        HttpRequestBuilder()
        .set_url("https://api.example.com/messages")
        .set_method("POST")
        .add_header("Content-Type", "application/json")
        .add_header("Accept", "application/json")
        .set_body("{\"message\":\"Hello\"}")
        .set_timeout_ms(3000)
        .build()
    )
    print(request.summary())

    base_doc = DocumentPrototype("Prototype", ["Intro"])
    copy = base_doc.clone_prototype()
    copy.title = "Prototype Copy"
    copy.add_paragraph("Second paragraph")
    print(base_doc.summary())
    print(copy.summary())

    config = AppConfig()
    config.set("env", "dev")
    print(f"Singleton env: {config.get('env', 'local')}")

    validation = ValidationHandler()
    auth = AuthHandler()
    business = BusinessHandler()
    validation.link_with(auth).link_with(business)

    request = Request("req-1", "token:abc; payload data")
    print(validation.handle(request))

    buffer = TextBuffer()
    invoker = CommandInvoker()
    print(invoker.run(AppendTextCommand(buffer, "Hello")))
    print(invoker.run(AppendTextCommand(buffer, " Command")))
    print(f"Command history size: {invoker.history_size()}")

    collection = ItemCollection()
    collection.add(Item("1", "Alpha"))
    collection.add(Item("2", "Beta"))
    for item in collection:
        print(f"Iterator item: {item.id}={item.name}")

    room = ChatMediator()
    alice = UserParticipant("alice", room)
    bob = UserParticipant("bob", room)
    room.register(alice)
    room.register(bob)
    alice.send("Mediator hello")

    editor = TextEditor()
    editor.append("Hello")
    snapshot = editor.save()
    editor.append(" Memento")
    print(f"Memento current: {editor.current()}")
    editor.restore(snapshot)
    print(f"Memento restored: {editor.current()}")

    bus = EventBus()
    obs_a = LoggingObserver("A")
    obs_b = LoggingObserver("B")
    bus.subscribe(obs_a)
    bus.subscribe(obs_b)
    bus.publish("message.sent", "Observer hello")

    doc = DocumentContext(DraftState())
    print(f"State {doc.current_state()}: {doc.process('State message')}")
    doc.set_state(PublishedState())
    print(f"State {doc.current_state()}: {doc.process('State message')}")

    summary = SummaryReportGenerator()
    detailed = DetailedReportGenerator()
    print(summary.generate("Template Method report"))
    print(detailed.generate("Line one\nLine two"))

    root = ElementNode("div")
    root.add(TextNode("Visitor root: "))
    root.add(ElementNode("span").add(TextNode("child")))
    renderer = RenderVisitor()
    print(root.accept(renderer))

    email_creator = EmailNotificationCreator()
    sms_creator = SmsNotificationCreator()
    print(email_creator.notify("Factory Method message"))
    print(sms_creator.notify("Factory Method message"))

    light = LightUiFactory()
    dark = DarkUiFactory()
    print(f"{light.create_button().render()},{light.create_checkbox().render()}")
    print(f"{dark.create_button().render()},{dark.create_checkbox().render()}")


if __name__ == "__main__":
    main()