package lab2;

public class MortarMine extends Projectile {

    public MortarMine() {
        super("Мина 82 мм", 3.1, 0.082, 0.45, 211, 60);
    }

    private MortarMine(MortarMine source) {
        super(source);
    }

    @Override
    public Projectile copy() {
        return new MortarMine(this);
    }
}
