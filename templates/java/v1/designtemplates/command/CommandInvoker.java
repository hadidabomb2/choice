package designtemplates.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandInvoker {
    private final Deque<Command> history = new ArrayDeque<>();

    public String run(Command command) {
        history.push(command);
        return command.execute();
    }

    public int historySize() {
        return history.size();
    }
}
