package lab8;

public class TrajectoryCalculator {
    private static final double GRAVITY = 9.81;
    private static final double AIR_DENSITY = 1.225;
    private static final double TIME_STEP = 0.01;

    public TrajectoryResult calculate(ShotRequest request) {
        double area = Math.PI * request.getDiameter() * request.getDiameter() / 4.0;
        double k = 0.5 * AIR_DENSITY * request.getDragCoefficient() * area / request.getMass();
        double angle = Math.toRadians(request.getAngle());
        double x = 0.0;
        double y = 0.0;
        double vx = request.getSpeed() * Math.cos(angle);
        double vy = request.getSpeed() * Math.sin(angle);
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
        return new TrajectoryResult(time, x, maxHeight);
    }
}
