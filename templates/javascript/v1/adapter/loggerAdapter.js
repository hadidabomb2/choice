export class LegacyLoggerAdapter {
  constructor(legacy) {
    this.legacy = legacy;
  }

  log(level, message) {
    this.legacy.write(`[${level}] ${message}`);
  }
}
