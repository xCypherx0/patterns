package lab1;

public final class Environment {
    private static Environment instance;

    private double gravity = 9.81;
    private double airDensity = 1.225;
    private double timeStep = 0.01;

    private Environment() {
    }

    public static synchronized Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getAirDensity() {
        return airDensity;
    }

    public void setAirDensity(double airDensity) {
        this.airDensity = airDensity;
    }

    public double getTimeStep() {
        return timeStep;
    }

    public void setTimeStep(double timeStep) {
        this.timeStep = timeStep;
    }
}
