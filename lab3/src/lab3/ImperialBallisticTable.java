package lab3;

public class ImperialBallisticTable {
    private static final double GRAVITY_FEET = 32.174;
    private static final double MILS_IN_CIRCLE = 6400.0;

    public double rangeYards(double feetPerSecond, double elevationMils) {
        double angle = 2.0 * Math.PI * elevationMils / MILS_IN_CIRCLE;
        double rangeFeet = feetPerSecond * feetPerSecond * Math.sin(2.0 * angle) / GRAVITY_FEET;
        return rangeFeet / 3.0;
    }
}
