package lab7;

import java.util.ArrayList;
import java.util.List;

public class Gun {
    private static final double GRAVITY = 9.81;
    private static final double AIR_DENSITY = 1.225;
    private static final double TIME_STEP = 0.01;
    private static final double MASS = 21.76;
    private static final double DIAMETER = 0.122;
    private static final double DRAG = 0.30;

    private final List<Trajectory> journal = new ArrayList<>();
    private double angle = 45.0;
    private double speed = 690.0;

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int shotCount() {
        return journal.size();
    }

    public Trajectory lastShot() {
        return journal.get(journal.size() - 1);
    }

    public void removeLastShot() {
        journal.remove(journal.size() - 1);
    }

    public Trajectory fire() {
        double k = 0.5 * AIR_DENSITY * DRAG * Math.PI * DIAMETER * DIAMETER / 4.0 / MASS;
        double radians = Math.toRadians(angle);
        double x = 0.0;
        double y = 0.0;
        double vx = speed * Math.cos(radians);
        double vy = speed * Math.sin(radians);
        double time = 0.0;
        List<TrajectoryPoint> points = new ArrayList<>();
        points.add(new TrajectoryPoint(time, x, y, speed));

        while (true) {
            double v = Math.hypot(vx, vy);
            vx += -k * v * vx * TIME_STEP;
            vy += (-GRAVITY - k * v * vy) * TIME_STEP;
            double nextX = x + vx * TIME_STEP;
            double nextY = y + vy * TIME_STEP;
            if (nextY < 0.0) {
                double part = y / (y - nextY);
                points.add(new TrajectoryPoint(time + TIME_STEP * part,
                        x + (nextX - x) * part, 0.0, Math.hypot(vx, vy)));
                break;
            }
            x = nextX;
            y = nextY;
            time += TIME_STEP;
            points.add(new TrajectoryPoint(time, x, y, Math.hypot(vx, vy)));
        }
        Trajectory trajectory = new Trajectory(points);
        journal.add(trajectory);
        return trajectory;
    }
}
