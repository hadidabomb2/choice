package chain;

public class AuthHandler extends Handler {
    @Override
    protected String process(Request request) {
        if (request.getPayload().contains("token:")) {
            return null;
        }
        return "Auth failed for request: " + request.getId();
    }
}
