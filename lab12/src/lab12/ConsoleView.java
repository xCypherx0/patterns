package lab12;

public class ConsoleView implements BallisticsView {
    public void show(double speed, double angle, Ballistics.Result r) {
        System.out.printf("V0 %.0f м/с, угол %.0f: дальность %.1f м, высота %.1f м, время %.2f с%n",
                speed, angle, r.range(), r.height(), r.time());
    }
    public void error(String message) { System.out.println("Ошибка ввода: " + message); }
}
