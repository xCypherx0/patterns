package lab4;

public class Simulator {
    private static final double TIME_STEP = 0.01;

    public TrajectoryResult simulate(ForceModel forces, double speed, double angleDeg) {
        double angle = Math.toRadians(angleDeg);
        Vector position = new Vector(0.0, 0.0);
        Vector velocity = new Vector(speed * Math.cos(angle), speed * Math.sin(angle));
        double time = 0.0;
        double maxHeight = 0.0;
        while (true) {
            velocity = velocity.plus(forces.acceleration(velocity).times(TIME_STEP));
            Vector next = position.plus(velocity.times(TIME_STEP));
            if (next.y() < 0.0) {
                double part = position.y() / (position.y() - next.y());
                double range = position.x() + (next.x() - position.x()) * part;
                return new TrajectoryResult(time + TIME_STEP * part, range, maxHeight);
            }
            position = next;
            time += TIME_STEP;
            maxHeight = Math.max(maxHeight, position.y());
        }
    }
}
