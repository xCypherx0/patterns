package lab8;

public class ParameterHandler extends ShotHandler {

    @Override
    protected Decision process(ShotRequest request) {
        if (request.getMass() <= 0 || request.getDiameter() <= 0) {
            return reject("масса и калибр должны быть положительными");
        }
        if (request.getSpeed() <= 0) {
            return reject("начальная скорость должна быть положительной");
        }
        return null;
    }

    @Override
    protected String getName() {
        return "Проверка параметров";
    }
}
