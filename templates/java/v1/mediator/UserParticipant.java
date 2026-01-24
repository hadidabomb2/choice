package mediator;

public class UserParticipant extends Participant {
    private static final String USER_PREFIX = "User ";
    private static final String RECEIVED_INFIX = " received from ";
    private static final String MESSAGE_SEPARATOR = ": ";

    public UserParticipant(String id, MessageMediator mediator) {
        super(id, mediator);
    }

    @Override
    public String onMessage(String fromId, String message) {
        return USER_PREFIX + id + RECEIVED_INFIX + fromId + MESSAGE_SEPARATOR + message;
    }
}
