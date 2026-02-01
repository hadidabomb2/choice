const DRAFT_STATE_NAME = "draft";
const PUBLISHED_STATE_NAME = "published";
const DRAFT_PREFIX = "DRAFT: ";
const PUBLISHED_PREFIX = "PUBLISHED: ";

function formatDraft(input) {
  return `${DRAFT_PREFIX}${input}`;
}

function formatPublished(input) {
  return `${PUBLISHED_PREFIX}${input.toUpperCase()}`;
}

export const draftState = {
  name: DRAFT_STATE_NAME,
  handle(input) {
    return formatDraft(input);
  }
};

export const publishedState = {
  name: PUBLISHED_STATE_NAME,
  handle(input) {
    return formatPublished(input);
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
