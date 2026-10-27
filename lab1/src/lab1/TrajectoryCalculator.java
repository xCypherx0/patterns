package lab1;

public class TrajectoryCalculator {

    public TrajectoryResult calculate(Projectile projectile) {
        Environment env = Environment.getInstance();
        double g = env.getGravity();
        double dt = env.getTimeStep();
        double k = 0.5 * env.getAirDensity() * projectile.getDragCoefficient()
                * projectile.crossSection() / projectile.getMass();

        double angle = Math.toRadians(projectile.getAngle());
        double x = 0.0;
        double y = projectile.getStartHeight();
        double vx = projectile.getSpeed() * Math.cos(angle);
        double vy = projectile.getSpeed() * Math.sin(angle);
        double time = 0.0;
        double maxHeight = y;

        while (true) {
            double v = Math.hypot(vx, vy);
            vx += -k * v * vx * dt;
            vy += (-g - k * v * vy) * dt;
            double nextX = x + vx * dt;
            double nextY = y + vy * dt;
            if (nextY < 0.0) {
                double part = y / (y - nextY);
                x += (nextX - x) * part;
                time += dt * part;
                break;
            }
            x = nextX;
            y = nextY;
            time += dt;
            maxHeight = Math.max(maxHeight, y);
        }
        return new TrajectoryResult(time, x, maxHeight, Math.hypot(vx, vy));
    }
}
