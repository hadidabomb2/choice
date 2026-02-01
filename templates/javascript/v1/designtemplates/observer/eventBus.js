export class EventBus {
  constructor() {
    this.observers = new Set();
  }

  subscribe(observer) {
    this.observers.add(observer);
  }

  unsubscribe(observer) {
    this.observers.delete(observer);
  }

  publish(event, payload) {
    for (const observer of this.observers) {
      observer.update(event, payload);
    }
  }
}

export class LoggingObserver {
  constructor(name) {
    this.name = name;
  }

  update(event, payload) {
    console.log(`Observer ${this.name} saw ${event}: ${payload}`);
  }
}
