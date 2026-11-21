package lab10;

public class StatisticsObserver implements FlightObserver {
    private int count;
    private double height;
    public void updated(String event, double time, double x, double y) {
        count++;
        height = Math.max(height, y);
    }
    public int count() { return count; }
    public double height() { return height; }
}
