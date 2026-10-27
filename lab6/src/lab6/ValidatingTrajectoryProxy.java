package lab6;

public class ValidatingTrajectoryProxy implements TrajectoryService {
    private static final double MAX_SPEED = 1800.0;

    private final TrajectoryService service;

    public ValidatingTrajectoryProxy(TrajectoryService service) {
        this.service = service;
    }

    @Override
    public TrajectoryResult calculate(ShotRequest request) {
        if (request.mass() <= 0 || request.diameter() <= 0 || request.dragCoefficient() < 0) {
            throw new IllegalArgumentException("параметры снаряда должны быть положительными");
        }
        if (request.speed() <= 0 || request.speed() > MAX_SPEED) {
            throw new IllegalArgumentException("скорость вне диапазона от 0 до " + (int) MAX_SPEED + " м/с");
        }
        if (request.angle() <= 0 || request.angle() >= 90) {
            throw new IllegalArgumentException("угол вне диапазона от 0 до 90 градусов");
        }
        return service.calculate(request);
    }
}
