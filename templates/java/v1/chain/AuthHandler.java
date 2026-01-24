package chain;

public class AuthHandler extends Handler {
    private static final String TOKEN_PREFIX = "token:";
    private static final String AUTH_FAILED_PREFIX = "Auth failed for request: ";

    @Override
    protected String process(Request request) {
        if (request.getPayload().contains(TOKEN_PREFIX)) {
            return null;
        }
        return AUTH_FAILED_PREFIX + request.getId();
    }
}
