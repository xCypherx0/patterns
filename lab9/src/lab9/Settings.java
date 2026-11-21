package lab9;

public class Settings {
    private double speed = 690;
    private double angle = 45;
    private double density = 1.225;
    public record Snapshot(double speed, double angle, double density) {}
    public Snapshot save() { return new Snapshot(speed, angle, density); }
    public void restore(Snapshot snapshot) {
        speed = snapshot.speed();
        angle = snapshot.angle();
        density = snapshot.density();
    }
    public void set(double speed, double angle, double density) {
        Ballistics.calculate(speed, angle, density);
        this.speed = speed;
        this.angle = angle;
        this.density = density;
    }
    public double speed() { return speed; }
    public double angle() { return angle; }
    public double density() { return density; }
}
