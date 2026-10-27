package lab3;

public abstract class MotionModel {
    protected static final double GRAVITY = 9.81;

    protected final Integrator integrator;

    protected MotionModel(Integrator integrator) {
        this.integrator = integrator;
    }

    protected abstract double[] acceleration(double vx, double vy);

    public abstract String getName();

    public String getIntegratorName() {
        return integrator.getName();
    }

    public TrajectoryResult simulate(double speed, double angleDeg, double dt) {
        double angle = Math.toRadians(angleDeg);
        State state = new State(0, 0, speed * Math.cos(angle), speed * Math.sin(angle));
        double time = 0.0;
        double maxHeight = 0.0;
        while (true) {
            State next = integrator.step(state, this::acceleration, dt);
            if (next.y() < 0.0) {
                double part = state.y() / (state.y() - next.y());
                double range = state.x() + (next.x() - state.x()) * part;
                return new TrajectoryResult(time + dt * part, range, maxHeight);
            }
            state = next;
            time += dt;
            maxHeight = Math.max(maxHeight, state.y());
        }
    }
}
