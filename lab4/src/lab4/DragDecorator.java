package lab4;

public class DragDecorator extends ForceDecorator {
    private final double dragFactor;

    public DragDecorator(ForceModel inner, double dragFactor) {
        super(inner);
        this.dragFactor = dragFactor;
    }

    @Override
    protected Vector extra(Vector velocity) {
        return velocity.times(-dragFactor * velocity.length());
    }

    @Override
    protected String label() {
        return "сопротивление воздуха";
    }
}
