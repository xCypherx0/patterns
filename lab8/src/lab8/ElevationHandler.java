package lab8;

public class ElevationHandler extends ShotHandler {
    private final double minAngle;
    private final double maxAngle;

    public ElevationHandler(double minAngle, double maxAngle) {
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
    }

    @Override
    protected Decision process(ShotRequest request) {
        if (request.getAngle() < minAngle || request.getAngle() > maxAngle) {
            return reject(String.format("угол %.0f вне пределов наведения от %.0f до %.0f",
                    request.getAngle(), minAngle, maxAngle));
        }
        return null;
    }

    @Override
    protected String getName() {
        return "Пределы наведения";
    }
}
