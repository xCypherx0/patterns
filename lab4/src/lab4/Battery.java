package lab4;

import java.util.ArrayList;
import java.util.List;

public class Battery implements FireUnit {
    private final String name;
    private final List<FireUnit> units = new ArrayList<>();

    public Battery(String name) {
        this.name = name;
    }

    public Battery add(FireUnit unit) {
        units.add(unit);
        return this;
    }

    public void remove(FireUnit unit) {
        units.remove(unit);
    }

    @Override
    public int gunCount() {
        return units.stream().mapToInt(FireUnit::gunCount).sum();
    }

    @Override
    public double salvoMass() {
        return units.stream().mapToDouble(FireUnit::salvoMass).sum();
    }

    @Override
    public double maxRange() {
        return units.stream().mapToDouble(FireUnit::maxRange).max().orElse(0.0);
    }

    @Override
    public void report(String indent) {
        System.out.printf("%s%s: орудий %d, масса залпа %.1f кг, досягаемость %.0f м%n",
                indent, name, gunCount(), salvoMass(), maxRange());
        for (FireUnit unit : units) {
            unit.report(indent + "    ");
        }
    }
}
