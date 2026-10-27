package lab2;

public class Howitzer extends Launcher {

    @Override
    protected Projectile createProjectile() {
        return new ArtilleryShell();
    }

    @Override
    public String getName() {
        return "Гаубица Д-30";
    }
}
