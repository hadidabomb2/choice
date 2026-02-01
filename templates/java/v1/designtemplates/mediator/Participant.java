package designtemplates.mediator;

public abstract class Participant {
    protected final String id;
    protected final MessageMediator mediator;

    protected Participant(String id, MessageMediator mediator) {
        this.id = id;
        this.mediator = mediator;
    }

    public String getId() {
        return id;
    }

    public void send(String message) {
        mediator.broadcast(id, message);
    }

    public abstract String onMessage(String fromId, String message);
}
