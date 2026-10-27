package lab3;

public class ImperialTableAdapter implements RangeCalculator {
    private static final double FEET_IN_METER = 3.28084;
    private static final double METERS_IN_YARD = 0.9144;
    private static final double MILS_IN_DEGREE = 6400.0 / 360.0;

    private final ImperialBallisticTable table;

    public ImperialTableAdapter(ImperialBallisticTable table) {
        this.table = table;
    }

    @Override
    public double rangeMeters(double speedMetersPerSecond, double angleDegrees) {
        double feetPerSecond = speedMetersPerSecond * FEET_IN_METER;
        double mils = angleDegrees * MILS_IN_DEGREE;
        return table.rangeYards(feetPerSecond, mils) * METERS_IN_YARD;
    }

    @Override
    public String getName() {
        return "Таблица в футах и ярдах (адаптер)";
    }
}
