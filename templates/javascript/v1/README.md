# JavaScript Template Notes

This template demonstrates the decorator (function wrapper), simple factory, factory method, abstract factory, strategy, facade, flyweight, proxy, composite, bridge, adapter, builder, prototype, singleton, chain of responsibility, command, iterator, mediator, memento, observer, state, template method, and visitor patterns using only Node.js built-ins.

Includes:
- `decorator/perf.js` — wrapper to measure time, memory, and locals created
- `factory/shapeFactory.js` — simple factory example
- `factorymethod/notifications.js` — factory method example
- `abstractfactory/uiFactory.js` — abstract factory example
- `strategy/greeterStrategies.js` — strategy example
- `facade/greetingFacade.js` — facade example
- `flyweight/textStyleFactory.js` — flyweight example
- `proxy/timedProxy.js` — proxy example
- `composite/nodes.js` — composite example
- `bridge/formatters.js`, `bridge/notifiers.js` — bridge example
- `adapter/legacyLogger.js`, `adapter/loggerAdapter.js` — adapter example
- `builder/httpRequestBuilder.js` — builder example
- `prototype/documentPrototype.js` — prototype example
- `singleton/appConfig.js` — singleton example
- `chain/handlers.js` — chain of responsibility example
- `command/commands.js` — command example
- `iterator/collection.js` — iterator example
- `mediator/chatMediator.js` — mediator example
- `memento/textEditor.js` — memento example
- `observer/eventBus.js` — observer example
- `state/states.js` — state example
- `templatemethod/reportGenerators.js` — template method example
- `visitor/nodes.js` — visitor example
- `app/index.js` — example usage

Run:
- `node app/index.js`

Style Notes:
- `app/index.js` keeps each pattern demo in a small, named function.
- Numeric literals are promoted to named constants for clarity.