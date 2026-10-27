package lab1;

public final class Projectile {
    private final String name;
    private final double mass;
    private final double diameter;
    private final double dragCoefficient;
    private final double speed;
    private final double angle;
    private final double startHeight;

    private Projectile(Builder builder) {
        this.name = builder.name;
        this.mass = builder.mass;
        this.diameter = builder.diameter;
        this.dragCoefficient = builder.dragCoefficient;
        this.speed = builder.speed;
        this.angle = builder.angle;
        this.startHeight = builder.startHeight;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAngle() {
        return angle;
    }

    public double getStartHeight() {
        return startHeight;
    }

    public double crossSection() {
        return Math.PI * diameter * diameter / 4.0;
    }

    public static final class Builder {
        private String name = "Снаряд";
        private double mass = 1.0;
        private double diameter = 0.05;
        private double dragCoefficient = 0.3;
        private double speed = 100.0;
        private double angle = 45.0;
        private double startHeight = 0.0;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder mass(double mass) {
            this.mass = mass;
            return this;
        }

        public Builder diameter(double diameter) {
            this.diameter = diameter;
            return this;
        }

        public Builder dragCoefficient(double dragCoefficient) {
            this.dragCoefficient = dragCoefficient;
            return this;
        }

        public Builder speed(double speed) {
            this.speed = speed;
            return this;
        }

        public Builder angle(double angle) {
            this.angle = angle;
            return this;
        }

        public Builder startHeight(double startHeight) {
            this.startHeight = startHeight;
            return this;
        }

        public Projectile build() {
            if (mass <= 0 || diameter <= 0) {
                throw new IllegalStateException("Масса и калибр должны быть положительными");
            }
            if (speed <= 0) {
                throw new IllegalStateException("Начальная скорость должна быть положительной");
            }
            if (angle <= 0 || angle >= 90) {
                throw new IllegalStateException("Угол бросания должен лежать в интервале от 0 до 90 градусов");
            }
            if (startHeight < 0) {
                throw new IllegalStateException("Начальная высота не может быть отрицательной");
            }
            return new Projectile(this);
        }
    }
}
