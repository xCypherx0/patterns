package lab2;

import java.util.HashMap;
import java.util.Map;

public class ProjectileRegistry {
    private final Map<String, Projectile> prototypes = new HashMap<>();

    public void register(String key, Projectile prototype) {
        prototypes.put(key, prototype);
    }

    public Projectile create(String key) {
        Projectile prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Прототип не найден: " + key);
        }
        return prototype.copy();
    }

    public Projectile original(String key) {
        return prototypes.get(key);
    }
}
