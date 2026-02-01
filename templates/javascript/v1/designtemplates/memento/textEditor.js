const EMPTY_BUFFER = "";

export class TextSnapshot {
  constructor(state) {
    this.state = state;
  }
}

export class TextEditor {
  constructor() {
    this.buffer = EMPTY_BUFFER;
  }

  append(text) {
    this.buffer += text;
  }

  current() {
    return this.buffer;
  }

  save() {
    return new TextSnapshot(this.current());
  }

  restore(snapshot) {
    this.buffer = snapshot.state;
  }
}
