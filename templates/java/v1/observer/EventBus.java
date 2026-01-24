package observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void publish(String event, String payload) {
        for (Observer observer : observers) {
            observer.update(event, payload);
        }
    }
}
