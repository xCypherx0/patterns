package lab7;

public class SetSpeedCommand implements Command {
    private final Gun gun;
    private final double speed;
    private double previous;

    public SetSpeedCommand(Gun gun, double speed) {
        this.gun = gun;
        this.speed = speed;
    }

    @Override
    public void execute() {
        previous = gun.getSpeed();
        gun.setSpeed(speed);
    }

    @Override
    public void undo() {
        gun.setSpeed(previous);
    }

    @Override
    public String describe() {
        return String.format("установить скорость %.0f м/с", speed);
    }
}
