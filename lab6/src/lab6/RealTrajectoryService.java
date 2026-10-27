package lab6;

public class RealTrajectoryService implements TrajectoryService {
    private static final double GRAVITY = 9.81;
    private static final double TIME_STEP = 0.00002;
    private static final int TABLE_SIZE = 30001;

    private final double[] densityTable = new double[TABLE_SIZE];

    public RealTrajectoryService() {
        for (int height = 0; height < TABLE_SIZE; height++) {
            densityTable[height] = 1.225 * Math.exp(-height / 8500.0);
        }
        System.out.println("  [сервис] таблица плотности атмосферы построена, записей: " + TABLE_SIZE);
    }

    @Override
    public TrajectoryResult calculate(ShotRequest r) {
        double area = Math.PI * r.diameter() * r.diameter() / 4.0;
        double factor = 0.5 * r.dragCoefficient() * area / r.mass();
        double angle = Math.toRadians(r.angle());
        double x = 0.0;
        double y = 0.0;
        double vx = r.speed() * Math.cos(angle);
        double vy = r.speed() * Math.sin(angle);
        double time = 0.0;
        double maxHeight = 0.0;

        while (true) {
            double k = factor * densityTable[Math.min((int) y, TABLE_SIZE - 1)];
            double v = Math.hypot(vx, vy);
            vx += -k * v * vx * TIME_STEP;
            vy += (-GRAVITY - k * v * vy) * TIME_STEP;
            double nextY = y + vy * TIME_STEP;
            if (nextY < 0.0) {
                break;
            }
            x += vx * TIME_STEP;
            y = nextY;
            time += TIME_STEP;
            maxHeight = Math.max(maxHeight, y);
        }
        return new TrajectoryResult(time, x, maxHeight, Math.hypot(vx, vy));
    }
}
