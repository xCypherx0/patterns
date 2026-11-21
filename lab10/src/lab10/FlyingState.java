package lab10;

public class FlyingState implements FlightState {
    public void tick(Flight flight) { flight.advance(); }
    public String name() { return "В полете"; }
}
