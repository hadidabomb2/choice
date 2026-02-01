const configValues = new Map();
const EMPTY_DEFAULT = "";

const appConfig = {
  set(key, value) {
    configValues.set(key, value);
  },
  get(key, defaultValue = EMPTY_DEFAULT) {
    return configValues.has(key) ? configValues.get(key) : defaultValue;
  }
};

Object.freeze(appConfig);

export { appConfig };
