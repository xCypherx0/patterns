package lab3;

import java.util.List;

public class Main {
    private static final double SPEED = 690.0;
    private static final double ANGLE = 45.0;
    private static final double MASS = 21.76;
    private static final double DIAMETER = 0.122;
    private static final double DRAG = 0.30;

    public static void main(String[] args) {
        Integrator euler = new EulerIntegrator();
        Integrator rungeKutta = new RungeKuttaIntegrator();
        List<MotionModel> models = List.of(
                new VacuumModel(euler),
                new VacuumModel(rungeKutta),
                new AirDragModel(euler, MASS, DIAMETER, DRAG),
                new AirDragModel(rungeKutta, MASS, DIAMETER, DRAG));

        System.out.println("Мост: модель движения и метод интегрирования меняются независимо");
        System.out.printf("Снаряд 122 мм, V0 = %.0f м/с, угол = %.0f градусов%n", SPEED, ANGLE);
        for (double dt : new double[]{0.5, 0.01}) {
            System.out.printf("%nШаг интегрирования %.2f с%n", dt);
            System.out.printf("%-10s %-15s %10s %14s %11s%n",
                    "Модель", "Интегратор", "Время, с", "Дальность, м", "Высота, м");
            for (MotionModel model : models) {
                TrajectoryResult r = model.simulate(SPEED, ANGLE, dt);
                System.out.printf("%-10s %-15s %10.2f %14.1f %11.1f%n",
                        model.getName(), model.getIntegratorName(),
                        r.flightTime(), r.range(), r.maxHeight());
            }
        }

        List<RangeCalculator> calculators = List.of(
                new ModelRangeCalculator(new VacuumModel(rungeKutta), 0.01),
                new ImperialTableAdapter(new ImperialBallisticTable()));

        System.out.println();
        System.out.println("Адаптер: старая таблица в имперских единицах за метрическим интерфейсом");
        System.out.printf("%-36s %12s %12s %12s%n", "Источник", "15 градусов", "30 градусов", "45 градусов");
        for (RangeCalculator calculator : calculators) {
            System.out.printf("%-36s %12.1f %12.1f %12.1f%n", calculator.getName(),
                    calculator.rangeMeters(SPEED, 15),
                    calculator.rangeMeters(SPEED, 30),
                    calculator.rangeMeters(SPEED, 45));
        }
    }
}
