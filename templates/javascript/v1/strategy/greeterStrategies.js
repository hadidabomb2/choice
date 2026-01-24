export const friendlyStrategy = {
  format(name) {
    return `Hey ${name}, great to meet you.`;
  }
};

export const formalStrategy = {
  format(name) {
    return `Hello ${name}. It is a pleasure to meet you.`;
  }
};

export const uppercaseStrategy = {
  format(name) {
    return `HELLO ${name} FROM STRATEGY`.toUpperCase();
  }
};

export class GreeterContext {
  constructor(strategy) {
    this.strategy = strategy;
  }

  setStrategy(strategy) {
    this.strategy = strategy;
  }

  greet(name) {
    return this.strategy.format(name);
  }
}
