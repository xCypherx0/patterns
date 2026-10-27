package lab2;

public class TrajectoryCalculator {
    private static final double GRAVITY = 9.81;
    private static final double AIR_DENSITY = 1.225;
    private static final double TIME_STEP = 0.01;

    public TrajectoryResult calculate(Projectile projectile) {
        double k = 0.5 * AIR_DENSITY * projectile.getDragCoefficient()
                * projectile.crossSection() / projectile.getMass();
        double angle = Math.toRadians(projectile.getAngle());
        double x = 0.0;
        double y = 0.0;
        double vx = projectile.getSpeed() * Math.cos(angle);
        double vy = projectile.getSpeed() * Math.sin(angle);
        double time = 0.0;
        double maxHeight = 0.0;

        while (true) {
            double v = Math.hypot(vx, vy);
            vx += -k * v * vx * TIME_STEP;
            vy += (-GRAVITY - k * v * vy) * TIME_STEP;
            double nextX = x + vx * TIME_STEP;
            double nextY = y + vy * TIME_STEP;
            if (nextY < 0.0) {
                double part = y / (y - nextY);
                x += (nextX - x) * part;
                time += TIME_STEP * part;
                break;
            }
            x = nextX;
            y = nextY;
            time += TIME_STEP;
            maxHeight = Math.max(maxHeight, y);
        }
        return new TrajectoryResult(time, x, maxHeight, Math.hypot(vx, vy));
    }
}
