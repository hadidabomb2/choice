export class TextNode {
  constructor(text) {
    this.text = text;
  }

  accept(visitor) {
    return visitor.visitText(this);
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

  accept(visitor) {
    return visitor.visitElement(this);
  }
}

export class RenderVisitor {
  visitText(textNode) {
    return textNode.text;
  }

  visitElement(elementNode) {
    const inner = elementNode.children.map((c) => c.accept(this)).join("");
    return `<${elementNode.tag}>${inner}</${elementNode.tag}>`;
  }
}
