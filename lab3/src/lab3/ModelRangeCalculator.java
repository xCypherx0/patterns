package lab3;

public class ModelRangeCalculator implements RangeCalculator {
    private final MotionModel model;
    private final double timeStep;

    public ModelRangeCalculator(MotionModel model, double timeStep) {
        this.model = model;
        this.timeStep = timeStep;
    }

    @Override
    public double rangeMeters(double speedMetersPerSecond, double angleDegrees) {
        return model.simulate(speedMetersPerSecond, angleDegrees, timeStep).range();
    }

    @Override
    public String getName() {
        return model.getName() + ", " + model.getIntegratorName();
    }
}
