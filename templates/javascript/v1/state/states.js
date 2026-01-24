export const draftState = {
  name: "draft",
  handle(input) {
    return `DRAFT: ${input}`;
  }
};

export const publishedState = {
  name: "published",
  handle(input) {
    return `PUBLISHED: ${input.toUpperCase()}`;
  }
};

export class DocumentContext {
  constructor(state) {
    this.state = state;
  }

  setState(state) {
    this.state = state;
  }

  process(input) {
    return this.state.handle(input);
  }

  currentState() {
    return this.state.name;
  }
}
