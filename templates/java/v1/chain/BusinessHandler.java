package chain;

public class BusinessHandler extends Handler {
    private static final String PROCESSED_PREFIX = "Processed request ";
    private static final String PAYLOAD_LENGTH_LABEL = " with payload length ";

    @Override
    protected String process(Request request) {
        return formatProcessed(request.getId(), request.getPayload().length());
    }

    private String formatProcessed(String requestId, int payloadLength) {
        return PROCESSED_PREFIX + requestId + PAYLOAD_LENGTH_LABEL + payloadLength;
    }
}
