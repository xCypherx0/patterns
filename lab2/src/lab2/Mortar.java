package lab2;

public class Mortar extends Launcher {

    @Override
    protected Projectile createProjectile() {
        return new MortarMine();
    }

    @Override
    public String getName() {
        return "Миномет 2Б14";
    }
}
