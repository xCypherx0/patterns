package lab9;

public class InputPanel {
    private final Mediator mediator;
    private double speed = 690;
    private double angle = 45;
    public InputPanel(Mediator mediator) { this.mediator = mediator; }
    public void set(double speed, double angle) {
        this.speed = speed;
        this.angle = angle;
        mediator.changed("input");
    }
    public double speed() { return speed; }
    public double angle() { return angle; }
}
