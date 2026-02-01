export class LegacyLoggerAdapter {
  constructor(legacy) {
    this.legacy = legacy;
  }

  log(level, message) {
    this.legacy.write(formatMessage(level, message));
  }
}

function formatMessage(level, message) {
  return `[${level}] ${message}`;
}
