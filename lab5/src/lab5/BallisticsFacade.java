package lab5;

public class BallisticsFacade {
    private final ProjectileTypeFactory factory;
    private final Atmosphere atmosphere = new Atmosphere();
    private final TrajectoryIntegrator integrator = new TrajectoryIntegrator();
    private final TrajectoryAnalyzer analyzer = new TrajectoryAnalyzer();
    private final ReportFormatter formatter = new ReportFormatter();

    public BallisticsFacade(ProjectileTypeFactory factory) {
        this.factory = factory;
    }

    public String header() {
        return formatter.header();
    }

    public String shoot(String typeName, double speed, double angle) {
        Shot shot = new Shot(factory.getType(typeName), speed, angle);
        return formatter.line(shot, calculate(shot));
    }

    public double range(String typeName, double speed, double angle) {
        return calculate(new Shot(factory.getType(typeName), speed, angle)).range();
    }

    public double bestAngle(String typeName, double speed) {
        double bestAngle = 0.0;
        double bestRange = 0.0;
        for (double angle = 20.0; angle <= 60.0; angle += 0.5) {
            double range = range(typeName, speed, angle);
            if (range > bestRange) {
                bestRange = range;
                bestAngle = angle;
            }
        }
        return bestAngle;
    }

    private TrajectoryResult calculate(Shot shot) {
        return analyzer.analyze(integrator.integrate(shot, atmosphere));
    }
}
