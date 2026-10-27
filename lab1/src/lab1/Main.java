package lab1;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Environment first = Environment.getInstance();
        Environment second = Environment.getInstance();
        System.out.println("Паттерн Одиночка: параметры среды");
        System.out.println("Обе ссылки указывают на один объект: " + (first == second));
        System.out.printf("g = %.2f м/с2, плотность воздуха = %.3f кг/м3, шаг = %.3f с%n%n",
                first.getGravity(), first.getAirDensity(), first.getTimeStep());

        List<Projectile> projectiles = List.of(
                Projectile.builder()
                        .name("Пуля 7,62 мм")
                        .mass(0.0096)
                        .diameter(0.00762)
                        .dragCoefficient(0.295)
                        .speed(830)
                        .angle(30)
                        .build(),
                Projectile.builder()
                        .name("Снаряд 122 мм")
                        .mass(21.76)
                        .diameter(0.122)
                        .dragCoefficient(0.30)
                        .speed(690)
                        .angle(45)
                        .build(),
                Projectile.builder()
                        .name("Мина 82 мм")
                        .mass(3.1)
                        .diameter(0.082)
                        .dragCoefficient(0.45)
                        .speed(211)
                        .angle(60)
                        .startHeight(150)
                        .build());

        TrajectoryCalculator calculator = new TrajectoryCalculator();

        System.out.println("Паттерн Строитель: расчет в воздухе");
        printTable(projectiles, calculator);

        second.setAirDensity(0.0);
        System.out.println();
        System.out.println("Плотность воздуха обнулена через вторую ссылку, расчет в вакууме");
        printTable(projectiles, calculator);

        System.out.println();
        try {
            Projectile.builder().name("Ошибочный").angle(120).build();
        } catch (IllegalStateException e) {
            System.out.println("Проверка строителя: " + e.getMessage());
        }
    }

    private static void printTable(List<Projectile> projectiles, TrajectoryCalculator calculator) {
        System.out.printf("%-16s %8s %6s %10s %13s %10s %9s%n",
                "Снаряд", "V0, м/с", "Угол", "Время, с", "Дальность, м", "Высота, м", "Vк, м/с");
        for (Projectile p : projectiles) {
            TrajectoryResult r = calculator.calculate(p);
            System.out.printf("%-16s %8.0f %6.0f %10.2f %13.1f %10.1f %9.1f%n",
                    p.getName(), p.getSpeed(), p.getAngle(),
                    r.flightTime(), r.range(), r.maxHeight(), r.impactSpeed());
        }
    }
}
