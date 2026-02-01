package designtemplates.mediator;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChatRoomMediator implements MessageMediator {
    private final Map<String, Participant> participants = new LinkedHashMap<>();

    @Override
    public void register(Participant participant) {
        participants.put(participant.getId(), participant);
    }

    @Override
    public void broadcast(String senderId, String message) {
        for (Participant participant : participants.values()) {
            if (!participant.getId().equals(senderId)) {
                String result = participant.onMessage(senderId, message);
                System.out.println(result);
            }
        }
    }
}
