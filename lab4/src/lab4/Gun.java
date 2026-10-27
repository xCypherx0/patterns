package lab4;

public class Gun implements FireUnit {
    private final String name;
    private final Shell shell;
    private final double speed;
    private final double angle;
    private final TrajectoryResult result;

    public Gun(String name, Shell shell, double speed, double angle, ForceModel forces) {
        this.name = name;
        this.shell = shell;
        this.speed = speed;
        this.angle = angle;
        this.result = new Simulator().simulate(forces, speed, angle);
    }

    @Override
    public int gunCount() {
        return 1;
    }

    @Override
    public double salvoMass() {
        return shell.mass();
    }

    @Override
    public double maxRange() {
        return result.range();
    }

    @Override
    public void report(String indent) {
        System.out.printf("%s%s: %s, V0 = %.0f м/с, угол %.0f, дальность %.0f м%n",
                indent, name, shell.name(), speed, angle, result.range());
    }
}
