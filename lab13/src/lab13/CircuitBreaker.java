package lab13;

import java.util.function.LongSupplier;
public class CircuitBreaker implements CalculationService {
    public enum State { CLOSED, OPEN, HALF_OPEN }
    private final CalculationService service;
    private final LongSupplier clock;
    private final int threshold;
    private final long cooldown;
    private State state = State.CLOSED;
    private int failures;
    private long openedAt;
    public CircuitBreaker(CalculationService service, LongSupplier clock, int threshold, long cooldown) {
        this.service = service; this.clock = clock;
        this.threshold = threshold; this.cooldown = cooldown;
    }
    public State state() { return state; }
    public Ballistics.Result calculate(double speed, double angle) {
        if (state == State.OPEN) {
            if (clock.getAsLong() - openedAt < cooldown)
                throw new IllegalStateException("Предохранитель разомкнут, вызов заблокирован");
            state = State.HALF_OPEN;
        }
        try {
            Ballistics.Result result = service.calculate(speed, angle);
            failures = 0;
            state = State.CLOSED;
            return result;
        } catch (RuntimeException e) {
            failures++;
            if (state == State.HALF_OPEN || failures >= threshold) {
                state = State.OPEN;
                openedAt = clock.getAsLong();
            }
            throw e;
        }
    }
}
