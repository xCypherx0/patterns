package lab7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TrajectoryIterator implements Iterator<TrajectoryPoint> {
    private final Trajectory trajectory;
    private int index;

    public TrajectoryIterator(Trajectory trajectory) {
        this.trajectory = trajectory;
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
        return trajectory.get(index++);
    }
}
