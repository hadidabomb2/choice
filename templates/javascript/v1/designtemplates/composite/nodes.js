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
    const content = this.children.map((child) => child.render()).join("");
    return renderElement(this.tag, content);
  }
}

function renderElement(tag, content) {
  return `<${tag}>${content}</${tag}>`;
}
