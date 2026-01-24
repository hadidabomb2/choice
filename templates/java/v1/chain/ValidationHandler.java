package chain;

public class ValidationHandler extends Handler {
    @Override
    protected String process(Request request) {
        if (request.getPayload() == null || request.getPayload().isBlank()) {
            return "Validation failed for request: " + request.getId();
        }
        return null;
    }
}
