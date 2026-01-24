const values = new Map();

const appConfig = {
  set(key, value) {
    values.set(key, value);
  },
  get(key, defaultValue = "") {
    return values.has(key) ? values.get(key) : defaultValue;
  }
};

Object.freeze(appConfig);

export { appConfig };
