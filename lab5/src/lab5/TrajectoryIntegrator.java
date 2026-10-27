package lab5;

import java.util.ArrayList;
import java.util.List;

public class TrajectoryIntegrator {
    private static final double GRAVITY = 9.81;
    private static final double TIME_STEP = 0.01;

    public List<Point> integrate(Shot shot, Atmosphere atmosphere) {
        double angle = Math.toRadians(shot.angle());
        double x = 0.0;
        double y = 0.0;
        double vx = shot.speed() * Math.cos(angle);
        double vy = shot.speed() * Math.sin(angle);
        double time = 0.0;
        List<Point> points = new ArrayList<>();
        points.add(new Point(time, x, y, shot.speed()));

        while (true) {
            double k = shot.type().dragFactor(atmosphere.density(y));
            double v = Math.hypot(vx, vy);
            vx += -k * v * vx * TIME_STEP;
            vy += (-GRAVITY - k * v * vy) * TIME_STEP;
            double nextX = x + vx * TIME_STEP;
            double nextY = y + vy * TIME_STEP;
            if (nextY < 0.0) {
                double part = y / (y - nextY);
                points.add(new Point(time + TIME_STEP * part, x + (nextX - x) * part,
                        0.0, Math.hypot(vx, vy)));
                return points;
            }
            x = nextX;
            y = nextY;
            time += TIME_STEP;
            points.add(new Point(time, x, y, Math.hypot(vx, vy)));
        }
    }
}
