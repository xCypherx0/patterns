package lab11;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лаба 11. Стратегия и Шаблонный метод");
        TrajectoryService service = new TrajectoryService(new AirStrategy());
        ReportTemplate table = new TableReport(service);
        table.generate(690, new double[]{30, 45, 60});
        System.out.println("Замена стратегии того же сервиса");
        service.setStrategy(new VacuumStrategy());
        table.generate(690, new double[]{30, 45, 60});
        System.out.println("Тот же алгоритм отчета, другой формат");
        new CsvReport(service).generate(690, new double[]{30, 45});
    }
}
