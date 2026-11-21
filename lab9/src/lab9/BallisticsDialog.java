package lab9;

public class BallisticsDialog implements Mediator {
    private final Settings settings = new Settings();
    private final History history = new History();
    private final InputPanel input = new InputPanel(this);
    private final ResultPanel output = new ResultPanel();
    public void edit(double speed, double angle) { input.set(speed, angle); }
    public void changed(String source) {
        if (source.equals("input")) {
            Ballistics.calculate(input.speed(), input.angle(), settings.density());
            history.remember(settings);
            settings.set(input.speed(), input.angle(), settings.density());
            show();
        }
    }
    public void undo() {
        if (history.undo(settings)) {
            System.out.println("Восстановлен предыдущий снимок настроек");
            show();
        } else System.out.println("История пуста");
    }
    public void show() {
        System.out.printf("V0 = %.0f м/с, угол = %.0f%n", settings.speed(), settings.angle());
        output.show(Ballistics.calculate(settings.speed(), settings.angle(), settings.density()));
    }
}
