package lab3;

public class VacuumModel extends MotionModel {

    public VacuumModel(Integrator integrator) {
        super(integrator);
    }

    @Override
    protected double[] acceleration(double vx, double vy) {
        return new double[]{0.0, -GRAVITY};
    }

    @Override
    public String getName() {
        return "Вакуум";
    }
}
