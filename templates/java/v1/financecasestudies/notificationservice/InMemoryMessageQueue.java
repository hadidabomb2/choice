package financecasestudies.notificationservice;

import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class InMemoryMessageQueue implements MessageQueue {
    private final List<MessageHandler> handlers = new CopyOnWriteArrayList<>();
    private final Queue<NotificationMessage> queue = new ConcurrentLinkedQueue<>();
    private final AtomicBoolean draining = new AtomicBoolean(false);

    @Override
    public void publish(NotificationMessage message) {
        queue.add(Objects.requireNonNull(message, "message"));
        drain();
    }

    @Override
    public void subscribe(MessageHandler handler) {
        handlers.add(Objects.requireNonNull(handler, "handler"));
    }

    private void drain() {
        if (!draining.compareAndSet(false, true)) {
            return;
        }

        try {
            NotificationMessage message;
            while ((message = queue.poll()) != null) {
                for (MessageHandler handler : handlers) {
                    handler.handle(message);
                }
            }
        } finally {
            draining.set(false);
            if (!queue.isEmpty()) {
                drain();
            }
        }
    }
}
