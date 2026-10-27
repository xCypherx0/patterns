package lab4;

public class GravityForce implements ForceModel {
    private static final double GRAVITY = 9.81;

    @Override
    public Vector acceleration(Vector velocity) {
        return new Vector(0.0, -GRAVITY);
    }

    @Override
    public String describe() {
        return "тяжесть";
    }
}
