package lab2;

public abstract class Launcher {
    private final TrajectoryCalculator calculator = new TrajectoryCalculator();

    protected abstract Projectile createProjectile();

    public abstract String getName();

    public String fire(double angle) {
        Projectile projectile = createProjectile();
        projectile.setAngle(angle);
        TrajectoryResult result = calculator.calculate(projectile);
        return String.format("%-18s %-15s %5.0f %10.2f %13.1f %10.1f",
                getName(), projectile.getName(), angle,
                result.flightTime(), result.range(), result.maxHeight());
    }
}
