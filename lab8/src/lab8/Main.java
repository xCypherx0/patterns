package lab8;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ShotHandler chain = new ParameterHandler();
        chain.linkWith(new ElevationHandler(7, 70))
                .linkWith(new SafetyHandler(13000, 6000))
                .linkWith(new AmmoHandler(3))
                .linkWith(new FireHandler());

        System.out.println("Цепочка: параметры -> наведение -> безопасность -> боекомплект -> выстрел");
        System.out.println("Гаубица 122 мм, полигон 13000 м, потолок 6000 м, боекомплект 3 снаряда");
        System.out.println();

        List<ShotRequest> requests = List.of(
                new ShotRequest(21.76, 0.122, 0.30, 690, 45),
                new ShotRequest(21.76, 0.122, 0.30, 690, 80),
                new ShotRequest(21.76, 0.122, 0.30, -50, 40),
                new ShotRequest(21.76, 0.122, 0.30, 690, 30),
                new ShotRequest(21.76, 0.122, 0.30, 690, 62),
                new ShotRequest(21.76, 0.122, 0.30, 565, 40),
                new ShotRequest(21.76, 0.122, 0.30, 335, 20),
                new ShotRequest(21.76, 0.122, 0.30, 335, 35));

        int number = 1;
        for (ShotRequest request : requests) {
            Decision decision = chain.handle(request);
            System.out.printf("Запрос %d: V0 = %.0f м/с, угол %.0f%n",
                    number++, request.getSpeed(), request.getAngle());
            System.out.printf("    %s [%s]: %s%n",
                    decision.accepted() ? "ВЫПОЛНЕН" : "ОТКЛОНЕН", decision.handler(), decision.message());
        }
    }
}
