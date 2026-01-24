export class Notification {
  send(_message) {
    throw new Error("Not implemented");
  }
}

export class EmailNotification extends Notification {
  send(message) {
    return `Email: ${message}`;
  }
}

export class SmsNotification extends Notification {
  send(message) {
    return `SMS: ${message}`;
  }
}

export class NotificationCreator {
  notify(message) {
    const notification = this.createNotification();
    return notification.send(message);
  }

  createNotification() {
    throw new Error("Not implemented");
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
