const LEGACY_PREFIX = "LEGACY: ";

export class LegacyLogger {
  write(message) {
    console.log(`${LEGACY_PREFIX}${message}`);
  }
}
