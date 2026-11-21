package lab12;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лаба 12. MVC: модель, представление, контроллер");
        BallisticsController controller = new BallisticsController(new BallisticsModel(), new ConsoleView());
        controller.calculate("690", "30");
        controller.calculate("690", "45");
        controller.calculate("565", "40");
        controller.calculate("текст", "45");
        controller.calculate("690", "95");
        controller.calculate("-10", "30");
        System.out.println("Контроллер обработал все запросы, приложение продолжает работу");
    }
}
