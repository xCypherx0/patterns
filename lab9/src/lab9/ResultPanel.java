package lab9;

public class ResultPanel {
    public void show(Ballistics.Result result) {
        System.out.printf("Дальность %.1f м, высота %.1f м, время %.2f с%n",
                result.range(), result.height(), result.time());
    }
}
