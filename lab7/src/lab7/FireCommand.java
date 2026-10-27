package lab7;

public class FireCommand implements Command {
    private final Gun gun;

    public FireCommand(Gun gun) {
        this.gun = gun;
    }

    @Override
    public void execute() {
        gun.fire();
    }

    @Override
    public void undo() {
        gun.removeLastShot();
    }

    @Override
    public String describe() {
        return "выстрел";
    }
}
