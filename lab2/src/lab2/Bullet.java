package lab2;

public class Bullet extends Projectile {

    public Bullet() {
        super("Пуля 7,62 мм", 0.0096, 0.00762, 0.295, 830, 10);
    }

    private Bullet(Bullet source) {
        super(source);
    }

    @Override
    public Projectile copy() {
        return new Bullet(this);
    }
}
