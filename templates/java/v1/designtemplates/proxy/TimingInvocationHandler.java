package designtemplates.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimingInvocationHandler implements InvocationHandler {
    private static final double NANOS_IN_MILLI = 1_000_000.0;
    private final Object target;

    public TimingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTimeNanos = System.nanoTime();
        Object result = method.invoke(target, args);
        long endTimeNanos = System.nanoTime();

        double durationMs = (endTimeNanos - startTimeNanos) / NANOS_IN_MILLI;
        System.out.println("PROXY: " + method.getName() + " took " + durationMs + " ms");
        return result;
    }
}
