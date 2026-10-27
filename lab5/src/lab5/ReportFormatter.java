package lab5;

public class ReportFormatter {

    public String header() {
        return String.format("%-15s %8s %6s %10s %14s %11s %9s",
                "Снаряд", "V0, м/с", "Угол", "Время, с", "Дальность, м", "Высота, м", "Vк, м/с");
    }

    public String line(Shot shot, TrajectoryResult result) {
        return String.format("%-15s %8.0f %6.0f %10.2f %14.1f %11.1f %9.1f",
                shot.type().getName(), shot.speed(), shot.angle(), result.flightTime(),
                result.range(), result.maxHeight(), result.impactSpeed());
    }
}
