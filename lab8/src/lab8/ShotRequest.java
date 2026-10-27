package lab8;

public class ShotRequest {
    private final double mass;
    private final double diameter;
    private final double dragCoefficient;
    private final double speed;
    private final double angle;
    private TrajectoryResult trajectory;

    public ShotRequest(double mass, double diameter, double dragCoefficient, double speed, double angle) {
        this.mass = mass;
        this.diameter = diameter;
        this.dragCoefficient = dragCoefficient;
        this.speed = speed;
        this.angle = angle;
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

    public TrajectoryResult getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(TrajectoryResult trajectory) {
        this.trajectory = trajectory;
    }
}
