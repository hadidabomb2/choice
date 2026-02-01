package designtemplates.proxy;

import java.lang.reflect.Proxy;

public class GreeterProxyFactory {
    public static GreeterService createTimed(GreeterService target) {
        return (GreeterService) Proxy.newProxyInstance(
                GreeterService.class.getClassLoader(),
                new Class<?>[]{GreeterService.class},
                new TimingInvocationHandler(target)
        );
    }
}
