package proxy;

public class GreeterServiceImpl implements GreeterService {
    @Override
    public String greet(String name) {
        return "Hello " + name + " from proxy";
    }
}
