package lab15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Лаба 15. Thread Pool: пакетный расчет траекторий");
        System.out.println("Пул из 3 потоков, 7 задач, результаты выводятся в порядке подачи");
        ExecutorService pool = Executors.newFixedThreadPool(3);
        try {
            List<Future<ShotTask.TaskResult>> futures = new ArrayList<>();
            for (int id = 1; id <= 7; id++) futures.add(pool.submit(new ShotTask(id, 10 + id * 10)));
            for (Future<ShotTask.TaskResult> future : futures) {
                ShotTask.TaskResult r = future.get();
                System.out.printf("Задача %d, %-15s, угол %.0f, дальность %.1f м%n",
                        r.id(), r.thread(), r.angle(), r.flight().range());
            }
            Future<ShotTask.TaskResult> invalid = pool.submit(new ShotTask(8, 95));
            try { invalid.get(); }
            catch (java.util.concurrent.ExecutionException e) {
                System.out.println("Ошибка задачи передана через Future: " + e.getCause().getMessage());
            }
        } finally {
            pool.shutdown();
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) pool.shutdownNow();
        }
        System.out.println("Пул завершен: " + pool.isTerminated());
    }
}
