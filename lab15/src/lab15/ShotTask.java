package lab15;

import java.util.concurrent.Callable;
public class ShotTask implements Callable<ShotTask.TaskResult> {
    public record TaskResult(int id, String thread, double angle, Ballistics.Result flight) {}
    private final int id;
    private final double angle;
    public ShotTask(int id, double angle) { this.id = id; this.angle = angle; }
    public TaskResult call() {
        return new TaskResult(id, Thread.currentThread().getName(), angle,
                Ballistics.calculate(690, angle, 1.225));
    }
}
