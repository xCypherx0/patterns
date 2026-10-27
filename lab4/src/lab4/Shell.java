package lab4;

public record Shell(String name, double mass, double diameter, double dragCoefficient) {
    private static final double AIR_DENSITY = 1.225;

    public double dragFactor() {
        double area = Math.PI * diameter * diameter / 4.0;
        return 0.5 * AIR_DENSITY * dragCoefficient * area / mass;
    }
}
