package lab10;

import java.util.ArrayList;
import java.util.List;
public class Flight {
    private final List<FlightObserver> observers = new ArrayList<>();
    private FlightState state = new ReadyState();
    private double time, x, y, vx, vy;
    private boolean apex;
    public void subscribe(FlightObserver observer) { observers.add(observer); }
    public void unsubscribe(FlightObserver observer) { observers.remove(observer); }
    private void notifyObservers(String event) {
        for (FlightObserver observer : List.copyOf(observers)) observer.updated(event, time, x, y);
    }
    public void launch(double speed, double angle) {
        if (!(state instanceof ReadyState)) throw new IllegalStateException("Повторный запуск запрещен");
        Ballistics.calculate(speed, angle, 1.225);
        vx = speed * Math.cos(Math.toRadians(angle));
        vy = speed * Math.sin(Math.toRadians(angle));
        state = new FlyingState();
        notifyObservers("Запуск");
    }
    public void tick() { state.tick(this); }
    public boolean landed() { return state instanceof LandedState; }
    public String stateName() { return state.name(); }
    void advance() {
        double dt = 0.01;
        double k = 0.5 * 1.225 * 0.30 * Math.PI * 0.122 * 0.122 / 4 / 21.76;
        double v = Math.hypot(vx, vy);
        vx -= k * v * vx * dt;
        double previousVy = vy;
        vy += (-9.81 - k * v * vy) * dt;
        double nx = x + vx * dt, ny = y + vy * dt;
        if (ny < 0) {
            double fraction = y / (y - ny);
            x += (nx - x) * fraction;
            y = 0;
            time += dt * fraction;
            state = new LandedState();
            notifyObservers("Приземление");
            return;
        }
        x = nx; y = ny; time += dt;
        if (!apex && previousVy > 0 && vy <= 0) {
            apex = true;
            notifyObservers("Вершина");
        }
    }
}
