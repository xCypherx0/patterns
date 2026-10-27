package lab7;

public interface Command {
    void execute();

    void undo();

    String describe();
}
