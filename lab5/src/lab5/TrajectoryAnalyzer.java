package lab5;

import java.util.List;

public class TrajectoryAnalyzer {

    public TrajectoryResult analyze(List<Point> points) {
        Point last = points.get(points.size() - 1);
        double maxHeight = points.stream().mapToDouble(Point::y).max().orElse(0.0);
        return new TrajectoryResult(last.time(), last.x(), maxHeight, last.speed());
    }
}
