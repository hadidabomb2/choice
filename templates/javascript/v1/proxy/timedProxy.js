import { performance } from "node:perf_hooks";

const DURATION_PRECISION = 3;

function formatDurationMs(endMs, startMs) {
  return (endMs - startMs).toFixed(DURATION_PRECISION);
}

export function createTimedProxy(target) {
  return new Proxy(target, {
    get(obj, prop, receiver) {
      const value = Reflect.get(obj, prop, receiver);
      if (typeof value !== "function") {
        return value;
      }

      return function (...args) {
        const startTimeMs = performance.now();
        const result = value.apply(this, args);
        const endTimeMs = performance.now();
        const durationMs = formatDurationMs(endTimeMs, startTimeMs);
        console.log(`PROXY: ${String(prop)} took ${durationMs} ms`);
        return result;
      };
    }
  });
}
