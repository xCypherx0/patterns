package lab2;

public abstract class Projectile {
    private final String name;
    private final double mass;
    private final double diameter;
    private final double dragCoefficient;
    private double speed;
    private double angle;

    protected Projectile(String name, double mass, double diameter, double dragCoefficient,
                         double speed, double angle) {
        this.name = name;
        this.mass = mass;
        this.diameter = diameter;
        this.dragCoefficient = dragCoefficient;
        this.speed = speed;
        this.angle = angle;
    }

    protected Projectile(Projectile source) {
        this(source.name, source.mass, source.diameter, source.dragCoefficient,
                source.speed, source.angle);
    }

    public abstract Projectile copy();

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double crossSection() {
        return Math.PI * diameter * diameter / 4.0;
    }
}
