package lab13;

public class Ballistics {
    public record Result(double time, double range, double height) {}

    public static Result calculate(double speed, double angle, double density) {
        if (!Double.isFinite(speed) || speed <= 0 || speed > 1800
                || !Double.isFinite(angle) || angle <= 0 || angle >= 90
                || !Double.isFinite(density) || density < 0) {
            throw new IllegalArgumentException("Недопустимые параметры полета");
        }
        double dt = 0.01;
        double k = 0.5 * density * 0.30 * Math.PI * 0.122 * 0.122 / 4 / 21.76;
        double vx = speed * Math.cos(Math.toRadians(angle));
        double vy = speed * Math.sin(Math.toRadians(angle));
        double x = 0, y = 0, time = 0, height = 0;
        for (int step = 0; step < 200000; step++) {
            double v = Math.hypot(vx, vy);
            vx -= k * v * vx * dt;
            vy += (-9.81 - k * v * vy) * dt;
            double nx = x + vx * dt;
            double ny = y + vy * dt;
            if (ny < 0) {
                double fraction = y / (y - ny);
                return new Result(time + fraction * dt, x + (nx - x) * fraction, height);
            }
            x = nx;
            y = ny;
            time += dt;
            height = Math.max(height, y);
        }
        throw new IllegalStateException("Превышено число шагов расчета");
    }
}
