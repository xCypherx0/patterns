package lab12;

public interface BallisticsView {
    void show(double speed, double angle, Ballistics.Result result);
    void error(String message);
}
