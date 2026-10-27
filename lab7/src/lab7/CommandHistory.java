package lab7;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandHistory {
    private final Deque<Command> executed = new ArrayDeque<>();

    public void execute(Command command) {
        command.execute();
        executed.push(command);
    }

    public Command undo() {
        if (executed.isEmpty()) {
            return null;
        }
        Command command = executed.pop();
        command.undo();
        return command;
    }

    public int size() {
        return executed.size();
    }
}
