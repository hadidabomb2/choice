export function createTimedProxy(target) {
  return new Proxy(target, {
    get(obj, prop, receiver) {
      const value = Reflect.get(obj, prop, receiver);
      if (typeof value !== "function") {
        return value;
      }

      return function (...args) {
        const start = performance.now();
        const result = value.apply(this, args);
        const end = performance.now();
        console.log(`PROXY: ${String(prop)} took ${(end - start).toFixed(3)} ms`);
        return result;
      };
    }
  });
}
