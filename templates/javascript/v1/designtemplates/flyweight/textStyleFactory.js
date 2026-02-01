const cache = new Map();
const KEY_SEPARATOR = "|";

function makeKey(fontFamily, fontSize, colorHex, bold, italic) {
  return [fontFamily, fontSize, colorHex, bold, italic].join(KEY_SEPARATOR);
}

function createStyle(fontFamily, fontSize, colorHex, bold, italic) {
  return Object.freeze({ fontFamily, fontSize, colorHex, bold, italic });
}

export function getTextStyle(fontFamily, fontSize, colorHex, bold, italic) {
  const key = makeKey(fontFamily, fontSize, colorHex, bold, italic);
  if (!cache.has(key)) {
    cache.set(key, createStyle(fontFamily, fontSize, colorHex, bold, italic));
  }

  return cache.get(key);
}

export function cacheSize() {
  return cache.size;
}

export class StyledText {
  constructor(text, style) {
    this.text = text;
    this.style = style;
  }

  render() {
    const { fontFamily, fontSize, colorHex, bold, italic } = this.style;
    return `[${fontFamily},${fontSize},${colorHex},bold=${bold},italic=${italic}] ${this.text}`;
  }
}
