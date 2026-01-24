export class DocumentPrototype {
  constructor(title, paragraphs) {
    this.title = title;
    this.paragraphs = [...paragraphs];
  }

  setTitle(title) {
    this.title = title;
  }

  addParagraph(paragraph) {
    this.paragraphs.push(paragraph);
  }

  clonePrototype() {
    return new DocumentPrototype(this.title, [...this.paragraphs]);
  }

  summary() {
    return `Document{title='${this.title}', paragraphs=${this.paragraphs.length}}`;
  }
}
