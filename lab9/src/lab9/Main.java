package lab9;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лаба 9. Посредник и Снимок. Снаряд 122 мм");
        BallisticsDialog dialog = new BallisticsDialog();
        dialog.show();
        System.out.println("Изменение угла через посредника");
        dialog.edit(690, 30);
        System.out.println("Изменение скорости и угла");
        dialog.edit(565, 40);
        dialog.undo();
        dialog.undo();
        dialog.undo();
    }
}
