package lab5;

public class Atmosphere {
    private static final double SEA_LEVEL_DENSITY = 1.225;
    private static final double SCALE_HEIGHT = 8500.0;

    public double density(double height) {
        return SEA_LEVEL_DENSITY * Math.exp(-Math.max(height, 0.0) / SCALE_HEIGHT);
    }
}
