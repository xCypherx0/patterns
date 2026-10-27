package lab7;

import java.util.Iterator;
import java.util.List;

public class Trajectory implements Iterable<TrajectoryPoint> {
    private final List<TrajectoryPoint> points;

    public Trajectory(List<TrajectoryPoint> points) {
        this.points = List.copyOf(points);
    }

    @Override
    public Iterator<TrajectoryPoint> iterator() {
        return new TrajectoryIterator(this);
    }

    public Iterable<TrajectoryPoint> every(double interval) {
        return () -> new TimeStepIterator(this, interval);
    }

    int size() {
        return points.size();
    }

    TrajectoryPoint get(int index) {
        return points.get(index);
    }
}
