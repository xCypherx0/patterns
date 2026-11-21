package lab14;

public class ShotJournal {
    private final ShotRepository repository;
    public ShotJournal(ShotRepository repository) { this.repository = repository; }
    public void calculateAndSave(int id, double speed, double angle) {
        Ballistics.Result result = Ballistics.calculate(speed, angle, 1.225);
        repository.save(new ShotRecord(id, speed, angle, result.range()));
    }
}
