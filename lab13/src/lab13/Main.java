package lab13;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лаба 13. Circuit Breaker, локальная модель отказа сервиса");
        System.out.println("Порог: 2 отказа, пауза: 1000 мс, время управляется вручную");
        UnstableService service = new UnstableService();
        ManualClock clock = new ManualClock();
        CircuitBreaker breaker = new CircuitBreaker(service, clock, 2, 1000);
        call("Отказ 1", breaker);
        call("Отказ 2", breaker);
        call("Блокировка", breaker);
        System.out.println("Реальных вызовов: " + service.calls());
        clock.advance(1000);
        call("Пробный вызов, сервис еще недоступен", breaker);
        call("Повторная блокировка", breaker);
        clock.advance(1000);
        service.setAvailable(true);
        call("Пробный вызов после восстановления", breaker);
        call("Обычный вызов", breaker);
        System.out.println("Всего реальных вызовов: " + service.calls());
    }
    private static void call(String label, CircuitBreaker breaker) {
        try {
            Ballistics.Result r = breaker.calculate(690, 45);
            System.out.printf("%s: дальность %.1f м, состояние %s%n", label, r.range(), breaker.state());
        } catch (IllegalStateException e) {
            System.out.println(label + ": " + e.getMessage() + ", состояние " + breaker.state());
        }
    }
}
