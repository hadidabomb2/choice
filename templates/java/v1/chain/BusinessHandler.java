package chain;

public class BusinessHandler extends Handler {
    @Override
    protected String process(Request request) {
        return "Processed request " + request.getId() + " with payload length " + request.getPayload().length();
    }
}
