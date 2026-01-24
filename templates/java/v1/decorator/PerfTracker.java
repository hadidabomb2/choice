package decorator;

import java.lang.reflect.Method;

public class PerfTracker {
    private static final String PERF_PREFIX = "PERF: ";
    private static final double NANOS_IN_MILLI = 1_000_000.0;
    private static final double BYTES_IN_KB = 1024.0;

    public static Object invoke(Object target, Method method, Object... args) throws Exception {
        String label = resolveLabel(method);

        long startTimeNanos = System.nanoTime();
        long startMemoryBytes = usedMemory();

        Object result = method.invoke(target, args);

        long endTimeNanos = System.nanoTime();
        long endMemoryBytes = usedMemory();
        int localsCount = method.getParameterCount();

        String report = buildReport(
            label,
            durationMs(startTimeNanos, endTimeNanos),
            memoryDeltaKb(startMemoryBytes, endMemoryBytes),
            localsCount
        );

        System.out.println(PERF_PREFIX + report);
        return result;
    }

    private static String resolveLabel(Method method) {
        String label = method.getAnnotation(Perf.class).name();
        return label.isEmpty() ? method.getName() : label;
    }

    private static double durationMs(long startTimeNanos, long endTimeNanos) {
        return (endTimeNanos - startTimeNanos) / NANOS_IN_MILLI;
    }

    private static double memoryDeltaKb(long startMemoryBytes, long endMemoryBytes) {
        return (endMemoryBytes - startMemoryBytes) / BYTES_IN_KB;
    }

    private static String buildReport(String label, double timeMs, double memoryKbDelta, int localsCount) {
        return "{" +
            "\"name\":\"" + label + "\", " +
            "\"time_ms\":" + timeMs + ", " +
            "\"memory_kb_delta\":" + memoryKbDelta + ", " +
            "\"locals_count\":" + localsCount +
            "}";
    }

    private static long usedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}