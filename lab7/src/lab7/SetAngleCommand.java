package lab7;

public class SetAngleCommand implements Command {
    private final Gun gun;
    private final double angle;
    private double previous;

    public SetAngleCommand(Gun gun, double angle) {
        this.gun = gun;
        this.angle = angle;
    }

    @Override
    public void execute() {
        previous = gun.getAngle();
        gun.setAngle(angle);
    }

    @Override
    public void undo() {
        gun.setAngle(previous);
    }

    @Override
    public String describe() {
        return String.format("установить угол %.0f", angle);
    }
}
