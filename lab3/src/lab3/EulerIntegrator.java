package lab3;

public class EulerIntegrator implements Integrator {

    @Override
    public State step(State s, Acceleration acceleration, double dt) {
        double[] a = acceleration.at(s.vx(), s.vy());
        return new State(
                s.x() + s.vx() * dt,
                s.y() + s.vy() * dt,
                s.vx() + a[0] * dt,
                s.vy() + a[1] * dt);
    }

    @Override
    public String getName() {
        return "Эйлер";
    }
}
