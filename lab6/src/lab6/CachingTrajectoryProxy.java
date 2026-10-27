package lab6;

import java.util.HashMap;
import java.util.Map;

public class CachingTrajectoryProxy implements TrajectoryService {
    private final Map<ShotRequest, TrajectoryResult> cache = new HashMap<>();
    private TrajectoryService service;
    private int hits;
    private int misses;

    @Override
    public TrajectoryResult calculate(ShotRequest request) {
        TrajectoryResult cached = cache.get(request);
        if (cached != null) {
            hits++;
            return cached;
        }
        misses++;
        if (service == null) {
            service = new RealTrajectoryService();
        }
        TrajectoryResult result = service.calculate(request);
        cache.put(request, result);
        return result;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }
}
