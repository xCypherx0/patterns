package lab11;

public class TableReport extends ReportTemplate {
    public TableReport(TrajectoryService service) { super(service); }
    protected void header() { System.out.println("Угол   Дальность, м   Высота, м   Время, с"); }
    protected void row(double speed, double angle, Ballistics.Result r) {
        System.out.printf("%4.0f %14.1f %11.1f %10.2f%n", angle, r.range(), r.height(), r.time());
    }
}
