package lab9;

import java.util.ArrayDeque;
import java.util.Deque;
public class History {
    private final Deque<Settings.Snapshot> snapshots = new ArrayDeque<>();
    public void remember(Settings settings) { snapshots.push(settings.save()); }
    public boolean undo(Settings settings) {
        if (snapshots.isEmpty()) return false;
        settings.restore(snapshots.pop());
        return true;
    }
}
