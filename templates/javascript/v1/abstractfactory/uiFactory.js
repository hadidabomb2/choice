const LIGHT_BUTTON_LABEL = "LightButton";
const LIGHT_CHECKBOX_LABEL = "LightCheckbox";
const DARK_BUTTON_LABEL = "DarkButton";
const DARK_CHECKBOX_LABEL = "DarkCheckbox";

export class LightButton {
  render() {
    return LIGHT_BUTTON_LABEL;
  }
}

export class LightCheckbox {
  render() {
    return LIGHT_CHECKBOX_LABEL;
  }
}

export class DarkButton {
  render() {
    return DARK_BUTTON_LABEL;
  }
}

export class DarkCheckbox {
  render() {
    return DARK_CHECKBOX_LABEL;
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
