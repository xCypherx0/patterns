package lab11;

public class CsvReport extends ReportTemplate {
    public CsvReport(TrajectoryService service) { super(service); }
    protected void header() { System.out.println("speed;angle;range;height;time"); }
    protected void row(double speed, double angle, Ballistics.Result r) {
        System.out.printf(java.util.Locale.ROOT, "%.0f;%.0f;%.1f;%.1f;%.2f%n",
                speed, angle, r.range(), r.height(), r.time());
    }
}
