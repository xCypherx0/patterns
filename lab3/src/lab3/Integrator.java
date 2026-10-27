package lab3;

public interface Integrator {
    State step(State state, Acceleration acceleration, double dt);

    String getName();
}
