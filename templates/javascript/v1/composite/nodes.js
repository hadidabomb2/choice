export class TextNode {
  constructor(text) {
    this.text = text;
  }

  render() {
    return this.text;
  }
}

export class ElementNode {
  constructor(tag) {
    this.tag = tag;
    this.children = [];
  }

  add(child) {
    this.children.push(child);
    return this;
  }

  render() {
    return `<${this.tag}>${this.children.map((c) => c.render()).join("")}</${this.tag}>`;
  }
}
