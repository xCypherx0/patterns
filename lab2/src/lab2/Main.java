package lab2;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Фабричный метод: каждая установка создает свой тип снаряда");
        System.out.printf("%-18s %-15s %5s %10s %13s %10s%n",
                "Установка", "Снаряд", "Угол", "Время, с", "Дальность, м", "Высота, м");
        List<Launcher> launchers = List.of(new Rifle(), new Howitzer(), new Mortar());
        for (Launcher launcher : launchers) {
            System.out.println(launcher.fire(30));
            System.out.println(launcher.fire(60));
        }

        ProjectileRegistry registry = new ProjectileRegistry();
        ArtilleryShell reduced = new ArtilleryShell();
        reduced.setChargeNumber(4);
        reduced.setSpeed(335);
        registry.register("shell-full", new ArtilleryShell());
        registry.register("shell-reduced", reduced);

        System.out.println();
        System.out.println("Прототип: таблица стрельбы строится на копиях снаряда");
        System.out.printf("%5s %22s %22s%n", "Угол", "Полный заряд, м", "Уменьшенный заряд, м");
        TrajectoryCalculator calculator = new TrajectoryCalculator();
        for (int angle = 15; angle <= 75; angle += 10) {
            Projectile full = registry.create("shell-full");
            Projectile small = registry.create("shell-reduced");
            full.setAngle(angle);
            small.setAngle(angle);
            System.out.printf("%5d %22.1f %22.1f%n", angle,
                    calculator.calculate(full).range(), calculator.calculate(small).range());
        }

        Projectile original = registry.original("shell-reduced");
        ArtilleryShell clone = (ArtilleryShell) registry.create("shell-reduced");
        clone.setAngle(75);
        System.out.println();
        System.out.println("Копия и прототип являются разными объектами: " + (clone != original));
        System.out.println("Класс копии: " + clone.getClass().getSimpleName()
                + ", номер заряда скопирован: " + clone.getChargeNumber());
        System.out.printf("Угол прототипа после изменения копии: %.0f, угол копии: %.0f%n",
                original.getAngle(), clone.getAngle());
    }
}
