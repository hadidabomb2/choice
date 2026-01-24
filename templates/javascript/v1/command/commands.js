export class TextBuffer {
  constructor() {
    this.buffer = "";
  }

  append(text) {
    this.buffer += text;
  }

  snapshot() {
    return this.buffer;
  }
}

export class AppendTextCommand {
  constructor(buffer, text) {
    this.buffer = buffer;
    this.text = text;
  }

  execute() {
    this.buffer.append(this.text);
    return this.buffer.snapshot();
  }
}

export class CommandInvoker {
  constructor() {
    this.history = [];
  }

  run(command) {
    this.history.push(command);
    return command.execute();
  }

  historySize() {
    return this.history.length;
  }
}
