package lab8;

public class FireHandler extends ShotHandler {

    @Override
    protected Decision process(ShotRequest request) {
        TrajectoryResult t = request.getTrajectory();
        return new Decision(true, getName(), String.format(
                "дальность %.0f м, высота %.0f м, время %.1f с",
                t.range(), t.maxHeight(), t.flightTime()));
    }

    @Override
    protected String getName() {
        return "Выстрел";
    }
}
