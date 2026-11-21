package lab10;

public class ConsoleObserver implements FlightObserver {
    public void updated(String event, double time, double x, double y) {
        System.out.printf("Событие %-14s t = %6.2f с, x = %8.1f м, y = %7.1f м%n", event, time, x, y);
    }
}
