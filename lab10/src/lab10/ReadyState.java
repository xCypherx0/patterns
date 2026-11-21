package lab10;

public class ReadyState implements FlightState {
    public void tick(Flight flight) { throw new IllegalStateException("Сначала выполните запуск"); }
    public String name() { return "Готов"; }
}
