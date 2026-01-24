package mediator;

public class UserParticipant extends Participant {
    public UserParticipant(String id, MessageMediator mediator) {
        super(id, mediator);
    }

    @Override
    public String onMessage(String fromId, String message) {
        return "User " + id + " received from " + fromId + ": " + message;
    }
}
