package mediator;

public interface MessageMediator {
    void register(Participant participant);
    void broadcast(String senderId, String message);
}
