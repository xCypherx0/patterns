package lab12;

public class BallisticsModel {
    public Ballistics.Result calculate(double speed, double angle) {
        return Ballistics.calculate(speed, angle, 1.225);
    }
}
