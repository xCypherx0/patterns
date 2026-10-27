package lab3;

public class RungeKuttaIntegrator implements Integrator {

    @Override
    public State step(State s, Acceleration acceleration, double dt) {
        double[] k1 = derivative(s.vx(), s.vy(), acceleration);
        double[] k2 = derivative(s.vx() + k1[2] * dt / 2, s.vy() + k1[3] * dt / 2, acceleration);
        double[] k3 = derivative(s.vx() + k2[2] * dt / 2, s.vy() + k2[3] * dt / 2, acceleration);
        double[] k4 = derivative(s.vx() + k3[2] * dt, s.vy() + k3[3] * dt, acceleration);
        return new State(
                s.x() + dt / 6 * (k1[0] + 2 * k2[0] + 2 * k3[0] + k4[0]),
                s.y() + dt / 6 * (k1[1] + 2 * k2[1] + 2 * k3[1] + k4[1]),
                s.vx() + dt / 6 * (k1[2] + 2 * k2[2] + 2 * k3[2] + k4[2]),
                s.vy() + dt / 6 * (k1[3] + 2 * k2[3] + 2 * k3[3] + k4[3]));
    }

    private double[] derivative(double vx, double vy, Acceleration acceleration) {
        double[] a = acceleration.at(vx, vy);
        return new double[]{vx, vy, a[0], a[1]};
    }

    @Override
    public String getName() {
        return "Рунге-Кутта 4";
    }
}
