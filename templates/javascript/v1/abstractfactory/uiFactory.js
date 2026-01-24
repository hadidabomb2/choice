export class LightButton {
  render() {
    return "LightButton";
  }
}

export class LightCheckbox {
  render() {
    return "LightCheckbox";
  }
}

export class DarkButton {
  render() {
    return "DarkButton";
  }
}

export class DarkCheckbox {
  render() {
    return "DarkCheckbox";
  }
}

export class LightUiFactory {
  createButton() {
    return new LightButton();
  }

  createCheckbox() {
    return new LightCheckbox();
  }
}

export class DarkUiFactory {
  createButton() {
    return new DarkButton();
  }

  createCheckbox() {
    return new DarkCheckbox();
  }
}
