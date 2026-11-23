package lab14;

import java.nio.file.Path;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Лаба 14. Repository: файловый журнал выстрелов");
        Path file = Path.of("journal.csv");
        ShotRepository repository = new CsvShotRepository(file);
        ShotJournal journal = new ShotJournal(repository);
        journal.calculateAndSave(1, 690, 45);
        journal.calculateAndSave(2, 690, 30);
        journal.calculateAndSave(3, 565, 40);
        System.out.println("Сохранено записей: " + repository.findAll().size());
        print(repository);
        journal.calculateAndSave(2, 335, 35);
        System.out.println("Обновление записи 2, угол 35, скорость 335 м/с");
        ShotRepository reopened = new CsvShotRepository(file);
        System.out.println("Повторное открытие файла другим экземпляром репозитория");
        print(reopened);
        reopened.delete(1);
        System.out.println("После удаления записи 1 осталось: " + reopened.findAll().size());
        System.out.println("Запись 99 найдена: " + reopened.findById(99).isPresent());
    }
    private static void print(ShotRepository repository) {
        for (ShotRecord r : repository.findAll())
            System.out.printf("ID %d: V0 %.0f м/с, угол %.0f, дальность %.1f м%n",
                    r.id(), r.speed(), r.angle(), r.range());
    }
}
