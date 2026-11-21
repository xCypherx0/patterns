package lab11;

public class TrajectoryService {
    private DragStrategy strategy;
    public TrajectoryService(DragStrategy strategy) { this.strategy = strategy; }
    public void setStrategy(DragStrategy strategy) { this.strategy = strategy; }
    public String modelName() { return strategy.name(); }
    public Ballistics.Result calculate(double speed, double angle) {
        return Ballistics.calculate(speed, angle, strategy.density());
    }
}
