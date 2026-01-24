const cache = new Map();

function makeKey(fontFamily, fontSize, colorHex, bold, italic) {
  return `${fontFamily}|${fontSize}|${colorHex}|${bold}|${italic}`;
}

export function getTextStyle(fontFamily, fontSize, colorHex, bold, italic) {
  const key = makeKey(fontFamily, fontSize, colorHex, bold, italic);
  if (!cache.has(key)) {
    const style = Object.freeze({ fontFamily, fontSize, colorHex, bold, italic });
    cache.set(key, style);
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
