export class ItemCollection {
  constructor() {
    this.items = [];
  }

  add(item) {
    this.items.push(item);
  }

  [Symbol.iterator]() {
    let index = 0;
    const items = this.items;
    return {
      next() {
        if (index < items.length) {
          return { value: items[index++], done: false };
        }
        return { value: undefined, done: true };
      }
    };
  }
}
