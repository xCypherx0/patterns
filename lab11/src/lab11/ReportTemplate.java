package lab11;

public abstract class ReportTemplate {
    protected final TrajectoryService service;
    protected ReportTemplate(TrajectoryService service) { this.service = service; }
    public final void generate(double speed, double[] angles) {
        System.out.println("Модель: " + service.modelName());
        header();
        for (double angle : angles) {
            Ballistics.Result result = service.calculate(speed, angle);
            row(speed, angle, result);
        }
        footer();
    }
    protected abstract void header();
    protected abstract void row(double speed, double angle, Ballistics.Result result);
    protected void footer() { System.out.println("Расчет завершен"); }
}
