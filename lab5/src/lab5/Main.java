package lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final String BULLET = "Пуля 7,62 мм";
    private static final String SHELL = "Снаряд 122 мм";
    private static final String MINE = "Мина 82 мм";

    public static void main(String[] args) {
        ProjectileTypeFactory factory = new ProjectileTypeFactory();
        Random random = new Random(12);
        List<Shot> shots = new ArrayList<>();
        for (int i = 0; i < 30000; i++) {
            ProjectileType type = switch (i % 3) {
                case 0 -> factory.getType(BULLET, 0.0096, 0.00762, 0.295);
                case 1 -> factory.getType(SHELL, 21.76, 0.122, 0.30);
                default -> factory.getType(MINE, 3.1, 0.082, 0.45);
            };
            shots.add(new Shot(type, 200 + random.nextInt(600), 10 + random.nextInt(70)));
        }

        System.out.println("Приспособленец: общие данные о типе снаряда хранятся один раз");
        System.out.println("Создано выстрелов: " + shots.size());
        System.out.println("Создано объектов типа снаряда: " + factory.typeCount());
        System.out.println("Выстрелы 1 и 4 используют один объект типа: "
                + (shots.get(0).type() == shots.get(3).type()));
        System.out.println("Выстрелы 1 и 2 используют один объект типа: "
                + (shots.get(0).type() == shots.get(1).type()));
        System.out.println();

        BallisticsFacade ballistics = new BallisticsFacade(factory);

        System.out.println("Фасад: расчет выстрела одним вызовом");
        System.out.println(ballistics.header());
        System.out.println(ballistics.shoot(BULLET, 830, 30));
        System.out.println(ballistics.shoot(SHELL, 690, 45));
        System.out.println(ballistics.shoot(MINE, 211, 60));

        System.out.println();
        System.out.println("Фасад: подбор угла наибольшей дальности");
        for (String type : List.of(BULLET, SHELL, MINE)) {
            double speed = type.equals(MINE) ? 211 : type.equals(SHELL) ? 690 : 830;
            double angle = ballistics.bestAngle(type, speed);
            System.out.printf("%-15s угол %.1f градуса, дальность %.1f м%n",
                    type, angle, ballistics.range(type, speed, angle));
        }
    }
}
