package lab8;

public class AmmoHandler extends ShotHandler {
    private int remaining;

    public AmmoHandler(int remaining) {
        this.remaining = remaining;
    }

    @Override
    protected Decision process(ShotRequest request) {
        if (remaining == 0) {
            return reject("боекомплект израсходован");
        }
        remaining--;
        return null;
    }

    @Override
    protected String getName() {
        return "Учет боекомплекта";
    }
}
