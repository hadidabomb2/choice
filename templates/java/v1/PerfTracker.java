import java.lang.reflect.Method;

public class PerfTracker {
    public static Object invoke(Object target, Method method, Object... args) throws Exception {
        String label = method.getAnnotation(Perf.class).name();
        if (label.isEmpty()) {
            label = method.getName();
        }

        long startTime = System.nanoTime();
        long startMem = usedMemory();

        Object result = method.invoke(target, args);

        long endTime = System.nanoTime();
        long endMem = usedMemory();

        int localsCount = method.getParameterCount(); // Java doesn't expose locals reliably

        System.out.println("PERF: {"
                + "\"name\":\"" + label + "\", "
                + "\"time_ms\":" + ((endTime - startTime) / 1_000_000.0) + ", "
                + "\"memory_kb_delta\":" + ((endMem - startMem) / 1024.0) + ", "
                + "\"locals_count\":" + localsCount
                + "}");

        return result;
    }

    private static long usedMemory() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }
}