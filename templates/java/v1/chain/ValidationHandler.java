package chain;

public class ValidationHandler extends Handler {
    private static final String VALIDATION_FAILED_PREFIX = "Validation failed for request: ";

    @Override
    protected String process(Request request) {
        if (request.getPayload() == null || request.getPayload().isBlank()) {
            return VALIDATION_FAILED_PREFIX + request.getId();
        }
        return null;
    }
}
