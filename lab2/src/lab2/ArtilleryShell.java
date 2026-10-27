package lab2;

public class ArtilleryShell extends Projectile {
    private int chargeNumber;

    public ArtilleryShell() {
        super("Снаряд 122 мм", 21.76, 0.122, 0.30, 690, 45);
        this.chargeNumber = 1;
    }

    private ArtilleryShell(ArtilleryShell source) {
        super(source);
        this.chargeNumber = source.chargeNumber;
    }

    @Override
    public Projectile copy() {
        return new ArtilleryShell(this);
    }

    public int getChargeNumber() {
        return chargeNumber;
    }

    public void setChargeNumber(int chargeNumber) {
        this.chargeNumber = chargeNumber;
    }
}
