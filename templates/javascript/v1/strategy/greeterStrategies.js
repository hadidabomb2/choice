const FRIENDLY_TEMPLATE = "Hey {name}, great to meet you.";
const FORMAL_TEMPLATE = "Hello {name}. It is a pleasure to meet you.";
const UPPERCASE_TEMPLATE = "Hello {name} from strategy";
const NAME_TOKEN = "{name}";

function formatTemplate(template, name) {
  return template.replace(NAME_TOKEN, name);
}

export const friendlyStrategy = {
  format(name) {
    return formatTemplate(FRIENDLY_TEMPLATE, name);
  }
};

export const formalStrategy = {
  format(name) {
    return formatTemplate(FORMAL_TEMPLATE, name);
  }
};

export const uppercaseStrategy = {
  format(name) {
    return formatTemplate(UPPERCASE_TEMPLATE, name).toUpperCase();
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
