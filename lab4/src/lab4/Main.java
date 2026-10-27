package lab4;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Shell shell = new Shell("снаряд 122 мм", 21.76, 0.122, 0.30);
        Shell mine = new Shell("мина 82 мм", 3.1, 0.082, 0.45);
        double k = shell.dragFactor();

        ForceModel gravity = new GravityForce();
        ForceModel air = new DragDecorator(gravity, k);
        ForceModel tailWind = new WindDecorator(air, 15, k);
        ForceModel headWind = new WindDecorator(air, -15, k);

        System.out.println("Декоратор: силы добавляются к базовой модели оберткой");
        System.out.printf("%-48s %10s %14s %11s%n", "Состав сил", "Время, с", "Дальность, м", "Высота, м");
        Simulator simulator = new Simulator();
        for (ForceModel forces : List.of(gravity, air, tailWind, headWind)) {
            TrajectoryResult r = simulator.simulate(forces, 690, 45);
            System.out.printf("%-48s %10.2f %14.1f %11.1f%n",
                    forces.describe(), r.flightTime(), r.range(), r.maxHeight());
        }

        ForceModel mineAir = new DragDecorator(new GravityForce(), mine.dragFactor());

        Battery first = new Battery("Батарея 1")
                .add(new Gun("Орудие 1", shell, 690, 45, air))
                .add(new Gun("Орудие 2", shell, 690, 30, air));
        Battery platoon = new Battery("Минометный взвод")
                .add(new Gun("Миномет 1", mine, 211, 45, mineAir))
                .add(new Gun("Миномет 2", mine, 211, 60, mineAir));
        Battery second = new Battery("Батарея 2")
                .add(new Gun("Орудие 3", shell, 565, 40, air))
                .add(platoon);
        Battery division = new Battery("Дивизион")
                .add(first)
                .add(second);

        System.out.println();
        System.out.println("Компоновщик: орудие и подразделение обрабатываются одинаково");
        division.report("");

        System.out.println();
        FireUnit single = new Gun("Отдельное орудие", shell, 690, 35, tailWind);
        for (FireUnit unit : List.of(single, platoon, division)) {
            System.out.printf("Орудий: %d, масса залпа: %.1f кг, досягаемость: %.0f м%n",
                    unit.gunCount(), unit.salvoMass(), unit.maxRange());
        }
    }
}
