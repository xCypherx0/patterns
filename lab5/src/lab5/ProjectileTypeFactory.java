package lab5;

import java.util.HashMap;
import java.util.Map;

public class ProjectileTypeFactory {
    private final Map<String, ProjectileType> types = new HashMap<>();

    public ProjectileType getType(String name, double mass, double diameter, double dragCoefficient) {
        return types.computeIfAbsent(name,
                key -> new ProjectileType(name, mass, diameter, dragCoefficient));
    }

    public ProjectileType getType(String name) {
        ProjectileType type = types.get(name);
        if (type == null) {
            throw new IllegalArgumentException("Неизвестный тип снаряда: " + name);
        }
        return type;
    }

    public int typeCount() {
        return types.size();
    }
}
