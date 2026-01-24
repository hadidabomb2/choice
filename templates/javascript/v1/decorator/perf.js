import { performance } from "node:perf_hooks";

export function profile(name, fn) {
  const label = name ?? fn.name ?? "anonymous";

  return function (...args) {
    const startMem = process.memoryUsage().heapUsed;
    const start = performance.now();

    const result = fn.apply(this, args);

    const end = performance.now();
    const endMem = process.memoryUsage().heapUsed;

    const localsCount = fn.length;

    console.log("PERF:", {
      name: label,
      time_ms: Number((end - start).toFixed(3)),
      memory_kb_delta: Number(((endMem - startMem) / 1024).toFixed(3)),
      locals_count: localsCount
    });

    return result;
  };
}