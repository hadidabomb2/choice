package designtemplates.chain;

public class Request {
    private final String id;
    private final String payload;

    public Request(String id, String payload) {
        this.id = id;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }
}
