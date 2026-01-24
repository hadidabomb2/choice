const NOT_IMPLEMENTED_ERROR = "Not implemented";
const EMAIL_PREFIX = "Email: ";
const SMS_PREFIX = "SMS: ";

export class Notification {
  send(_message) {
    throw new Error(NOT_IMPLEMENTED_ERROR);
  }
}

export class EmailNotification extends Notification {
  send(message) {
    return `${EMAIL_PREFIX}${message}`;
  }
}

export class SmsNotification extends Notification {
  send(message) {
    return `${SMS_PREFIX}${message}`;
  }
}

export class NotificationCreator {
  notify(message) {
    const notification = this.createNotification();
    return notification.send(message);
  }

  createNotification() {
    throw new Error(NOT_IMPLEMENTED_ERROR);
  }
}

export class EmailNotificationCreator extends NotificationCreator {
  createNotification() {
    return new EmailNotification();
  }
}

export class SmsNotificationCreator extends NotificationCreator {
  createNotification() {
    return new SmsNotification();
  }
}
