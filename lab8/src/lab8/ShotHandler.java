package lab8;

public abstract class ShotHandler {
    private ShotHandler next;

    public ShotHandler linkWith(ShotHandler next) {
        this.next = next;
        return next;
    }

    public Decision handle(ShotRequest request) {
        Decision decision = process(request);
        if (decision != null) {
            return decision;
        }
        if (next == null) {
            return new Decision(false, getName(), "цепочка закончилась без решения");
        }
        return next.handle(request);
    }

    protected abstract Decision process(ShotRequest request);

    protected abstract String getName();

    protected Decision reject(String reason) {
        return new Decision(false, getName(), reason);
    }
}
