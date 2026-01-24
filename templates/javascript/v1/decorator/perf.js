import { performance } from "node:perf_hooks";

const DEFAULT_LABEL = "anonymous";
const BYTES_IN_KB = 1024;
const TIME_PRECISION = 3;
const MEMORY_PRECISION = 3;

function resolveLabel(explicitLabel, fn) {
  return explicitLabel ?? fn.name ?? DEFAULT_LABEL;
}

function formatDurationMs(endMs, startMs) {
  return Number((endMs - startMs).toFixed(TIME_PRECISION));
}

function formatMemoryDeltaKb(endBytes, startBytes) {
  return Number(((endBytes - startBytes) / BYTES_IN_KB).toFixed(MEMORY_PRECISION));
}

export function profile(name, fn) {
  const label = resolveLabel(name, fn);

  return function (...args) {
    const startMemoryBytes = process.memoryUsage().heapUsed;
    const startTimeMs = performance.now();

    const result = fn.apply(this, args);

    const endTimeMs = performance.now();
    const endMemoryBytes = process.memoryUsage().heapUsed;
    const localsCount = fn.length;

    console.log("PERF:", {
      name: label,
      time_ms: formatDurationMs(endTimeMs, startTimeMs),
      memory_kb_delta: formatMemoryDeltaKb(endMemoryBytes, startMemoryBytes),
      locals_count: localsCount
    });

    return result;
  };
}