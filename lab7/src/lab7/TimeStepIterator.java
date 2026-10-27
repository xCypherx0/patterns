package lab7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TimeStepIterator implements Iterator<TrajectoryPoint> {
    private final Trajectory trajectory;
    private final double interval;
    private int index;
    private double nextTime;

    public TimeStepIterator(Trajectory trajectory, double interval) {
        this.trajectory = trajectory;
        this.interval = interval;
    }

    @Override
    public boolean hasNext() {
        return index < trajectory.size();
    }

    @Override
    public TrajectoryPoint next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        TrajectoryPoint point = trajectory.get(index);
        nextTime += interval;
        int last = trajectory.size() - 1;
        if (index == last) {
            index = trajectory.size();
        } else {
            while (index < last && trajectory.get(index).time() < nextTime - 1e-9) {
                index++;
            }
        }
        return point;
    }
}
