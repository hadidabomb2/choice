package designtemplates.chain;

public abstract class Handler {
    private static final String UNHANDLED_PREFIX = "Unhandled request: ";
    private Handler next;

    public Handler linkWith(Handler next) {
        this.next = next;
        return next;
    }

    public String handle(Request request) {
        String result = process(request);
        if (result != null) {
            return result;
        }
        if (next == null) {
            return formatUnhandled(request.getId());
        }
        return next.handle(request);
    }

    protected abstract String process(Request request);

    private String formatUnhandled(String requestId) {
        return UNHANDLED_PREFIX + requestId;
    }
}
