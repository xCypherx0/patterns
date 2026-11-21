package lab10;

public class LandedState implements FlightState {
    public void tick(Flight flight) { throw new IllegalStateException("Полет уже завершен"); }
    public String name() { return "Приземлился"; }
}
