package lab13;

public class UnstableService implements CalculationService {
    private boolean available;
    private int calls;
    public void setAvailable(boolean available) { this.available = available; }
    public int calls() { return calls; }
    public Ballistics.Result calculate(double speed, double angle) {
        calls++;
        if (!available) throw new IllegalStateException("Сервис расчета недоступен");
        return Ballistics.calculate(speed, angle, 1.225);
    }
}
