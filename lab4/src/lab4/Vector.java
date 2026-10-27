package lab4;

public record Vector(double x, double y) {

    public Vector plus(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector times(double factor) {
        return new Vector(x * factor, y * factor);
    }

    public double length() {
        return Math.hypot(x, y);
    }
}
