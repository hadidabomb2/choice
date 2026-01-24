# Java Template Notes

This template demonstrates the decorator (annotation-based), simple factory, factory method, abstract factory, strategy, facade, flyweight, proxy, composite, bridge, adapter, builder, prototype, singleton, chain of responsibility, command, iterator, mediator, memento, observer, state, template method, and visitor patterns using only the Java standard library.

Includes:
- `app/Main.java` — demo of annotation perf + factory
- `decorator/Perf.java` — @Perf annotation
- `decorator/PerfTracker.java` — reflection-based perf tracker
- `factory/Shape.java`, `factory/Circle.java`, `factory/Square.java`, `factory/ShapeFactory.java` — simple factory example
- `factorymethod/Notification.java`, `factorymethod/EmailNotification.java`, `factorymethod/SmsNotification.java`, `factorymethod/NotificationCreator.java`, `factorymethod/EmailNotificationCreator.java`, `factorymethod/SmsNotificationCreator.java` — factory method example
- `abstractfactory/Button.java`, `abstractfactory/Checkbox.java`, `abstractfactory/UiFactory.java`, `abstractfactory/LightButton.java`, `abstractfactory/LightCheckbox.java`, `abstractfactory/DarkButton.java`, `abstractfactory/DarkCheckbox.java`, `abstractfactory/LightUiFactory.java`, `abstractfactory/DarkUiFactory.java` — abstract factory example
- `strategy/GreeterStrategy.java`, `strategy/FriendlyStrategy.java`, `strategy/FormalStrategy.java`, `strategy/UppercaseStrategy.java`, `strategy/GreeterContext.java` — strategy example
- `facade/FacadeResult.java`, `facade/GreetingFacade.java` — facade example
- `flyweight/TextStyle.java`, `flyweight/TextStyleFactory.java`, `flyweight/StyledText.java` — flyweight example
- `proxy/GreeterService.java`, `proxy/GreeterServiceImpl.java`, `proxy/TimingInvocationHandler.java`, `proxy/GreeterProxyFactory.java` — proxy example
- `composite/Node.java`, `composite/TextNode.java`, `composite/ElementNode.java` — composite example
- `bridge/Formatter.java`, `bridge/TextFormatter.java`, `bridge/JsonFormatter.java`, `bridge/Notifier.java`, `bridge/EmailNotifier.java`, `bridge/SmsNotifier.java` — bridge example
- `adapter/Logger.java`, `adapter/LegacyLogger.java`, `adapter/LegacyLoggerAdapter.java` — adapter example
- `builder/HttpRequest.java` — builder example
- `prototype/Prototype.java`, `prototype/Document.java` — prototype example
- `singleton/AppConfig.java` — singleton example
- `chain/Request.java`, `chain/Handler.java`, `chain/ValidationHandler.java`, `chain/AuthHandler.java`, `chain/BusinessHandler.java` — chain of responsibility example
- `command/Command.java`, `command/TextBuffer.java`, `command/AppendTextCommand.java`, `command/CommandInvoker.java` — command example
- `iterator/Item.java`, `iterator/ItemCollection.java` — iterator example
- `mediator/MessageMediator.java`, `mediator/ChatRoomMediator.java`, `mediator/Participant.java`, `mediator/UserParticipant.java` — mediator example
- `memento/TextEditor.java`, `memento/TextSnapshot.java` — memento example
- `observer/Observer.java`, `observer/EventBus.java`, `observer/LoggingObserver.java` — observer example
- `state/State.java`, `state/DraftState.java`, `state/PublishedState.java`, `state/DocumentContext.java` — state example
- `templatemethod/ReportGenerator.java`, `templatemethod/SummaryReportGenerator.java`, `templatemethod/DetailedReportGenerator.java` — template method example
- `visitor/Node.java`, `visitor/TextNode.java`, `visitor/ElementNode.java`, `visitor/NodeVisitor.java`, `visitor/RenderVisitor.java` — visitor example

Run:
- `javac app/Main.java decorator/*.java factory/*.java factorymethod/*.java abstractfactory/*.java strategy/*.java facade/*.java flyweight/*.java proxy/*.java composite/*.java bridge/*.java adapter/*.java builder/*.java prototype/*.java singleton/*.java chain/*.java command/*.java iterator/*.java mediator/*.java memento/*.java observer/*.java state/*.java templatemethod/*.java visitor/*.java`
- `java app.Main`

Style Notes:
- `app/Main.java` keeps each pattern demo in a small, named method.
- Numeric literals are promoted to named constants for clarity.