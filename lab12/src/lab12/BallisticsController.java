package lab12;

public class BallisticsController {
    private final BallisticsModel model;
    private final BallisticsView view;
    public BallisticsController(BallisticsModel model, BallisticsView view) {
        this.model = model;
        this.view = view;
    }
    public void calculate(String speedText, String angleText) {
        try {
            double speed = Double.parseDouble(speedText);
            double angle = Double.parseDouble(angleText);
            view.show(speed, angle, model.calculate(speed, angle));
        } catch (IllegalArgumentException e) {
            view.error(e instanceof NumberFormatException ? "ожидаются числа" : e.getMessage());
        }
    }
}
