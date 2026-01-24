export class TextSnapshot {
  constructor(state) {
    this.state = state;
  }
}

export class TextEditor {
  constructor() {
    this.buffer = "";
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
