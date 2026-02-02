from designtemplates.decorator.perf import profile
from designtemplates.factory.shape_factory import ShapeFactory
from designtemplates.facade.greeting_facade import GreetingFacade
from designtemplates.flyweight.text_style_factory import TextStyleFactory, StyledText
from designtemplates.composite.nodes import ElementNode as CompositeElementNode, TextNode as CompositeTextNode
from designtemplates.proxy.timed_proxy import TimedProxy
from designtemplates.bridge.formatters import JsonFormatter, TextFormatter
from designtemplates.bridge.notifiers import EmailNotifier, SmsNotifier
from designtemplates.adapter.legacy_logger import LegacyLogger
from designtemplates.adapter.logger_adapter import LegacyLoggerAdapter
from designtemplates.builder.http_request_builder import HttpRequestBuilder
from designtemplates.prototype.document_prototype import DocumentPrototype
from designtemplates.singleton.app_config import AppConfig
from designtemplates.chain.handlers import ValidationHandler, AuthHandler, BusinessHandler, Request
from designtemplates.command.commands import TextBuffer, AppendTextCommand, CommandInvoker
from designtemplates.iterator.collection import ItemCollection, Item
from designtemplates.mediator.chat_mediator import ChatMediator, UserParticipant
from designtemplates.memento.text_editor import TextEditor
from designtemplates.observer.event_bus import EventBus, LoggingObserver
from designtemplates.state.states import DocumentContext, DraftState, PublishedState
from designtemplates.templatemethod.report_generators import SummaryReportGenerator, DetailedReportGenerator
from designtemplates.visitor.nodes import ElementNode as VisitorElementNode, TextNode as VisitorTextNode, RenderVisitor
from designtemplates.factorymethod.notifications import EmailNotificationCreator, SmsNotificationCreator
from designtemplates.abstractfactory.ui_factory import LightUiFactory, DarkUiFactory
from designtemplates.strategy.greeter_strategies import (
    FriendlyStrategy,
    FormalStrategy,
    UppercaseStrategy,
    GreeterContext,
)


class Greeter:
    @profile()
    def hello(self, name: str) -> str:
        return f"Hello world from {name}"


class GreeterService:
    def greet(self, name: str) -> str:
        return f"Hello {name} from proxy"


PROJECT_NAME = "{{projectName}}"
SHAPE_CIRCLE = "circle"
SHAPE_SQUARE = "square"
FONT_FAMILY = "Inter"
HEADLINE_FONT_SIZE = 18
BODY_FONT_SIZE = 14
HEADLINE_COLOR = "#111111"
BODY_COLOR = "#333333"
BRIDGE_MESSAGE = "Bridge message"
ADAPTER_MESSAGE = "Adapter message"
API_URL = "https://api.example.com/messages"
JSON_MEDIA_TYPE = "application/json"
JSON_BODY = '{"message":"Hello"}'
HTTP_TIMEOUT_MS = 3000
PROTOTYPE_TITLE = "Prototype"
PROTOTYPE_COPY_TITLE = "Prototype Copy"
ENV_KEY = "env"
ENV_VALUE = "dev"
ENV_DEFAULT = "local"
REQUEST_ID = "req-1"
REQUEST_PAYLOAD = "token:abc; payload data"
HELLO_TEXT = "Hello"
COMMAND_APPEND = " Command"
ITERATOR_ITEM_ONE_ID = "1"
ITERATOR_ITEM_ONE_NAME = "Alpha"
ITERATOR_ITEM_TWO_ID = "2"
ITERATOR_ITEM_TWO_NAME = "Beta"
MEDIATOR_USER_ALICE = "alice"
MEDIATOR_USER_BOB = "bob"
MEDIATOR_MESSAGE = "Mediator hello"
MEMENTO_APPEND = " Memento"
OBSERVER_EVENT = "message.sent"
OBSERVER_MESSAGE = "Observer hello"
STATE_MESSAGE = "State message"
TEMPLATE_METHOD_TITLE = "Template Method report"
TEMPLATE_METHOD_DETAILS = "Line one\nLine two"
FACTORY_METHOD_MESSAGE = "Factory Method message"


def run_decorator_example() -> None:
    greeter = Greeter()
    print(greeter.hello(PROJECT_NAME))


def run_simple_factory_example() -> None:
    shape = ShapeFactory.create(SHAPE_CIRCLE)
    print(shape.draw())


def run_strategy_example() -> None:
    context = GreeterContext(FriendlyStrategy())
    print(context.greet(PROJECT_NAME))

    context.set_strategy(FormalStrategy())
    print(context.greet(PROJECT_NAME))

    context.set_strategy(UppercaseStrategy())
    print(context.greet(PROJECT_NAME))


def run_facade_example() -> None:
    facade = GreetingFacade()
    result = facade.greet_and_draw(PROJECT_NAME, FormalStrategy(), SHAPE_SQUARE)
    print(result.greeting)
    print(result.drawing)


def run_flyweight_example() -> None:
    style_factory = TextStyleFactory()
    headline = style_factory.get_style(
        FONT_FAMILY, HEADLINE_FONT_SIZE, HEADLINE_COLOR, True, False
    )
    body = style_factory.get_style(FONT_FAMILY, BODY_FONT_SIZE, BODY_COLOR, False, False)
    headline_shared = style_factory.get_style(
        FONT_FAMILY, HEADLINE_FONT_SIZE, HEADLINE_COLOR, True, False
    )

    line1 = StyledText("Flyweight headline", headline)
    line2 = StyledText("Flyweight body", body)
    line3 = StyledText("Headline reused", headline_shared)

    print(line1.render())
    print(line2.render())
    print(line3.render())
    print(f"Flyweight cache size: {style_factory.cache_size()}")


def run_proxy_example() -> None:
    greeter = GreeterService()
    timed_greeter = TimedProxy(greeter)
    print(timed_greeter.greet(PROJECT_NAME))


def run_composite_example() -> None:
    composite_root = CompositeElementNode("div")
    composite_root.add(CompositeTextNode("Composite root: "))
    composite_root.add(CompositeElementNode("span").add(CompositeTextNode("child")))
    print(composite_root.render())


def run_bridge_example() -> None:
    email_text = EmailNotifier(TextFormatter())
    email_json = EmailNotifier(JsonFormatter())
    sms_text = SmsNotifier(TextFormatter())

    print(email_text.notify(BRIDGE_MESSAGE))
    print(email_json.notify(BRIDGE_MESSAGE))
    print(sms_text.notify(BRIDGE_MESSAGE))


def run_adapter_example() -> None:
    logger = LegacyLoggerAdapter(LegacyLogger())
    logger.log("INFO", ADAPTER_MESSAGE)


def run_builder_example() -> None:
    http_request = (
        HttpRequestBuilder()
        .set_url(API_URL)
        .set_method("POST")
        .add_header("Content-Type", JSON_MEDIA_TYPE)
        .add_header("Accept", JSON_MEDIA_TYPE)
        .set_body(JSON_BODY)
        .set_timeout_ms(HTTP_TIMEOUT_MS)
        .build()
    )
    print(http_request.summary())


def run_prototype_example() -> None:
    base_doc = DocumentPrototype(PROTOTYPE_TITLE, ["Intro"])
    copy = base_doc.clone_prototype()
    copy.title = PROTOTYPE_COPY_TITLE
    copy.add_paragraph("Second paragraph")
    print(base_doc.summary())
    print(copy.summary())


def run_singleton_example() -> None:
    config = AppConfig()
    config.set(ENV_KEY, ENV_VALUE)
    print(f"Singleton env: {config.get(ENV_KEY, ENV_DEFAULT)}")


def run_chain_example() -> None:
    validation = ValidationHandler()
    auth = AuthHandler()
    business = BusinessHandler()
    validation.link_with(auth).link_with(business)

    workflow_request = Request(REQUEST_ID, REQUEST_PAYLOAD)
    print(validation.handle(workflow_request))


def run_command_example() -> None:
    buffer = TextBuffer()
    invoker = CommandInvoker()
    print(invoker.run(AppendTextCommand(buffer, HELLO_TEXT)))
    print(invoker.run(AppendTextCommand(buffer, COMMAND_APPEND)))
    print(f"Command history size: {invoker.history_size()}")


def run_iterator_example() -> None:
    collection = ItemCollection()
    collection.add(Item(ITERATOR_ITEM_ONE_ID, ITERATOR_ITEM_ONE_NAME))
    collection.add(Item(ITERATOR_ITEM_TWO_ID, ITERATOR_ITEM_TWO_NAME))
    for item in collection:
        print(f"Iterator item: {item.id}={item.name}")


def run_mediator_example() -> None:
    room = ChatMediator()
    alice = UserParticipant(MEDIATOR_USER_ALICE, room)
    bob = UserParticipant(MEDIATOR_USER_BOB, room)
    room.register(alice)
    room.register(bob)
    alice.send(MEDIATOR_MESSAGE)


def run_memento_example() -> None:
    editor = TextEditor()
    editor.append(HELLO_TEXT)
    snapshot = editor.save()
    editor.append(MEMENTO_APPEND)
    print(f"Memento current: {editor.current()}")
    editor.restore(snapshot)
    print(f"Memento restored: {editor.current()}")


def run_observer_example() -> None:
    bus = EventBus()
    obs_a = LoggingObserver("A")
    obs_b = LoggingObserver("B")
    bus.subscribe(obs_a)
    bus.subscribe(obs_b)
    bus.publish(OBSERVER_EVENT, OBSERVER_MESSAGE)


def run_state_example() -> None:
    doc = DocumentContext(DraftState())
    print(f"State {doc.current_state()}: {doc.process(STATE_MESSAGE)}")
    doc.set_state(PublishedState())
    print(f"State {doc.current_state()}: {doc.process(STATE_MESSAGE)}")


def run_template_method_example() -> None:
    summary = SummaryReportGenerator()
    detailed = DetailedReportGenerator()
    print(summary.generate(TEMPLATE_METHOD_TITLE))
    print(detailed.generate(TEMPLATE_METHOD_DETAILS))


def run_visitor_example() -> None:
    visitor_root = VisitorElementNode("div")
    visitor_root.add(VisitorTextNode("Visitor root: "))
    visitor_root.add(VisitorElementNode("span").add(VisitorTextNode("child")))
    renderer = RenderVisitor()
    print(visitor_root.accept(renderer))


def run_factory_method_example() -> None:
    email_creator = EmailNotificationCreator()
    sms_creator = SmsNotificationCreator()
    print(email_creator.notify(FACTORY_METHOD_MESSAGE))
    print(sms_creator.notify(FACTORY_METHOD_MESSAGE))


def run_abstract_factory_example() -> None:
    light = LightUiFactory()
    dark = DarkUiFactory()
    print(f"{light.create_button().render()},{light.create_checkbox().render()}")
    print(f"{dark.create_button().render()},{dark.create_checkbox().render()}")


def main() -> None:
    run_decorator_example()
    run_simple_factory_example()
    run_strategy_example()
    run_facade_example()
    run_flyweight_example()
    run_proxy_example()
    run_composite_example()
    run_bridge_example()
    run_adapter_example()
    run_builder_example()
    run_prototype_example()
    run_singleton_example()
    run_chain_example()
    run_command_example()
    run_iterator_example()
    run_mediator_example()
    run_memento_example()
    run_observer_example()
    run_state_example()
    run_template_method_example()
    run_visitor_example()
    run_factory_method_example()
    run_abstract_factory_example()


if __name__ == "__main__":
    main()
