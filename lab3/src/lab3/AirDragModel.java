package lab3;

public class AirDragModel extends MotionModel {
    private static final double AIR_DENSITY = 1.225;

    private final double k;

    public AirDragModel(Integrator integrator, double mass, double diameter, double dragCoefficient) {
        super(integrator);
        double area = Math.PI * diameter * diameter / 4.0;
        this.k = 0.5 * AIR_DENSITY * dragCoefficient * area / mass;
    }

    @Override
    protected double[] acceleration(double vx, double vy) {
        double v = Math.hypot(vx, vy);
        return new double[]{-k * v * vx, -GRAVITY - k * v * vy};
    }

    @Override
    public String getName() {
        return "Воздух";
    }
}
