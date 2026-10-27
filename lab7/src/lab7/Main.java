package lab7;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Gun gun = new Gun();
        CommandHistory history = new CommandHistory();

        System.out.println("Команда: действия наводчика оформлены объектами");
        List<Command> commands = List.of(
                new SetAngleCommand(gun, 30),
                new FireCommand(gun),
                new SetSpeedCommand(gun, 565),
                new SetAngleCommand(gun, 50),
                new FireCommand(gun));
        for (Command command : commands) {
            history.execute(command);
            printState("Выполнено: " + command.describe(), gun);
        }

        System.out.println();
        for (int i = 0; i < 3; i++) {
            Command undone = history.undo();
            printState("Отменено: " + undone.describe(), gun);
        }
        System.out.println("Команд в истории: " + history.size());

        Trajectory trajectory = gun.lastShot();
        System.out.println();
        System.out.println("Итератор с шагом 5 с по траектории первого выстрела");
        System.out.printf("%9s %12s %11s %10s%n", "Время, с", "X, м", "Y, м", "V, м/с");
        for (TrajectoryPoint p : trajectory.every(5.0)) {
            System.out.printf("%9.2f %12.1f %11.1f %10.1f%n", p.time(), p.x(), p.y(), p.speed());
        }

        int count = 0;
        TrajectoryPoint top = null;
        for (TrajectoryPoint p : trajectory) {
            count++;
            if (top == null || p.y() > top.y()) {
                top = p;
            }
        }
        System.out.println();
        System.out.println("Полный обход обычным итератором, точек: " + count);
        System.out.printf("Вершина траектории: t = %.2f с, x = %.1f м, y = %.1f м%n",
                top.time(), top.x(), top.y());
    }

    private static void printState(String action, Gun gun) {
        System.out.printf("%-38s угол %2.0f, скорость %3.0f м/с, выстрелов %d%n",
                action, gun.getAngle(), gun.getSpeed(), gun.shotCount());
    }
}
