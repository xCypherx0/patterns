package lab2;

public class Rifle extends Launcher {

    @Override
    protected Projectile createProjectile() {
        return new Bullet();
    }

    @Override
    public String getName() {
        return "Винтовка";
    }
}
