const NOT_IMPLEMENTED_ERROR = "Not implemented";
const EMAIL_LABEL = "email";
const SMS_LABEL = "sms";

export class Notifier {
  constructor(formatter) {
    this.formatter = formatter;
  }

  notify(_message) {
    throw new Error(NOT_IMPLEMENTED_ERROR);
  }
}

export class EmailNotifier extends Notifier {
  notify(message) {
    return this.formatter.format(EMAIL_LABEL, message);
  }
}

export class SmsNotifier extends Notifier {
  notify(message) {
    return this.formatter.format(SMS_LABEL, message);
  }
}
