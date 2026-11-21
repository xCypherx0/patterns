package lab10;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лаба 10. Наблюдатель и Состояние");
        Flight flight = new Flight();
        StatisticsObserver stats = new StatisticsObserver();
        flight.subscribe(new ConsoleObserver());
        flight.subscribe(stats);
        System.out.println("Начальное состояние: " + flight.stateName());
        flight.launch(690, 45);
        System.out.println("Состояние после запуска: " + flight.stateName());
        while (!flight.landed()) flight.tick();
        System.out.println("Итоговое состояние: " + flight.stateName());
        System.out.printf("Статистика: событий %d, высота в вершине %.1f м%n", stats.count(), stats.height());
        try { flight.tick(); }
        catch (IllegalStateException e) { System.out.println("Контроль состояния: " + e.getMessage()); }
        flight.unsubscribe(stats);
    }
}
