package lab5;

public final class ProjectileType {
    private final String name;
    private final double mass;
    private final double diameter;
    private final double dragCoefficient;

    ProjectileType(String name, double mass, double diameter, double dragCoefficient) {
        this.name = name;
        this.mass = mass;
        this.diameter = diameter;
        this.dragCoefficient = dragCoefficient;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public double dragFactor(double airDensity) {
        double area = Math.PI * diameter * diameter / 4.0;
        return 0.5 * airDensity * dragCoefficient * area / mass;
    }
}
