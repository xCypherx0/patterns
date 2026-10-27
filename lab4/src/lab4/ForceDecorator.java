package lab4;

public abstract class ForceDecorator implements ForceModel {
    protected final ForceModel inner;

    protected ForceDecorator(ForceModel inner) {
        this.inner = inner;
    }

    @Override
    public Vector acceleration(Vector velocity) {
        return inner.acceleration(velocity).plus(extra(velocity));
    }

    @Override
    public String describe() {
        return inner.describe() + " + " + label();
    }

    protected abstract Vector extra(Vector velocity);

    protected abstract String label();
}
