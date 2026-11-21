package lab13;

import java.util.function.LongSupplier;
public class ManualClock implements LongSupplier {
    private long time;
    public long getAsLong() { return time; }
    public void advance(long milliseconds) { time += milliseconds; }
}
