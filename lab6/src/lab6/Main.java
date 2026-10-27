package lab6;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        CachingTrajectoryProxy cache = new CachingTrajectoryProxy();
        TrajectoryService service = new ValidatingTrajectoryProxy(cache);
        System.out.println("Заместители созданы, реальный сервис еще не создан");
        System.out.println();

        ShotRequest shell45 = new ShotRequest(21.76, 0.122, 0.30, 690, 45);
        ShotRequest shell30 = new ShotRequest(21.76, 0.122, 0.30, 690, 30);
        ShotRequest mine = new ShotRequest(3.1, 0.082, 0.45, 211, 60);
        List<ShotRequest> requests = List.of(shell45, shell30, shell45, mine, shell30, shell45, mine);

        System.out.printf("%3s %8s %6s %14s %11s %10s %12s%n",
                "N", "V0, м/с", "Угол", "Дальность, м", "Высота, м", "Время, с", "Расчет, мс");
        int number = 1;
        for (ShotRequest request : requests) {
            long start = System.nanoTime();
            TrajectoryResult r = service.calculate(request);
            double elapsed = (System.nanoTime() - start) / 1_000_000.0;
            System.out.printf("%3d %8.0f %6.0f %14.1f %11.1f %10.2f %12.3f%n", number++,
                    request.speed(), request.angle(), r.range(), r.maxHeight(), r.flightTime(), elapsed);
        }

        System.out.println();
        System.out.println("Обращений к реальному сервису: " + cache.getMisses());
        System.out.println("Ответов из кэша: " + cache.getHits());

        System.out.println();
        System.out.println("Защищающий заместитель отклоняет некорректные запросы");
        List<ShotRequest> wrong = List.of(
                new ShotRequest(21.76, 0.122, 0.30, 690, 95),
                new ShotRequest(21.76, 0.122, 0.30, 2500, 45),
                new ShotRequest(-1, 0.122, 0.30, 690, 45));
        for (ShotRequest request : wrong) {
            try {
                service.calculate(request);
            } catch (IllegalArgumentException e) {
                System.out.println("Отказ: " + e.getMessage());
            }
        }
        System.out.println("Обращений к реальному сервису после отказов: " + cache.getMisses());
    }
}
