package lab10;

public interface FlightState {
    void tick(Flight flight);
    String name();
}
