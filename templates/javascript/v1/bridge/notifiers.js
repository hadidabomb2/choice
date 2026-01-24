export class Notifier {
  constructor(formatter) {
    this.formatter = formatter;
  }

  notify(message) {
    throw new Error("Not implemented");
  }
}

export class EmailNotifier extends Notifier {
  notify(message) {
    return this.formatter.format("email", message);
  }
}

export class SmsNotifier extends Notifier {
  notify(message) {
    return this.formatter.format("sms", message);
  }
}
