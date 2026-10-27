package lab4;

public class WindDecorator extends ForceDecorator {
    private final double windSpeed;
    private final double dragFactor;

    public WindDecorator(ForceModel inner, double windSpeed, double dragFactor) {
        super(inner);
        this.windSpeed = windSpeed;
        this.dragFactor = dragFactor;
    }

    @Override
    protected Vector extra(Vector velocity) {
        return new Vector(dragFactor * windSpeed * Math.abs(windSpeed), 0.0);
    }

    @Override
    protected String label() {
        return String.format("ветер %+.0f м/с", windSpeed);
    }
}
