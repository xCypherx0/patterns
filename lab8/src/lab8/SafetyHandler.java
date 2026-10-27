package lab8;

public class SafetyHandler extends ShotHandler {
    private final TrajectoryCalculator calculator = new TrajectoryCalculator();
    private final double maxRange;
    private final double ceiling;

    public SafetyHandler(double maxRange, double ceiling) {
        this.maxRange = maxRange;
        this.ceiling = ceiling;
    }

    @Override
    protected Decision process(ShotRequest request) {
        TrajectoryResult trajectory = calculator.calculate(request);
        if (trajectory.range() > maxRange) {
            return reject(String.format("дальность %.0f м больше границы полигона %.0f м",
                    trajectory.range(), maxRange));
        }
        if (trajectory.maxHeight() > ceiling) {
            return reject(String.format("высота %.0f м больше разрешенной %.0f м",
                    trajectory.maxHeight(), ceiling));
        }
        request.setTrajectory(trajectory);
        return null;
    }

    @Override
    protected String getName() {
        return "Безопасность полигона";
    }
}
