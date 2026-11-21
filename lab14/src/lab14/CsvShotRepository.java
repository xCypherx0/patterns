package lab14;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class CsvShotRepository implements ShotRepository {
    private final Path file;
    public CsvShotRepository(Path file) { this.file = file; }
    public List<ShotRecord> findAll() {
        try {
            List<ShotRecord> records = new ArrayList<>();
            if (!Files.exists(file)) return records;
            for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
                String[] p = line.split(";");
                records.add(new ShotRecord(Integer.parseInt(p[0]), Double.parseDouble(p[1]),
                        Double.parseDouble(p[2]), Double.parseDouble(p[3])));
            }
            return records;
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalStateException("Не удалось прочитать журнал", e);
        }
    }
    public Optional<ShotRecord> findById(int id) {
        return findAll().stream().filter(r -> r.id() == id).findFirst();
    }
    public void save(ShotRecord record) {
        List<ShotRecord> records = findAll();
        records.removeIf(r -> r.id() == record.id());
        records.add(record);
        write(records);
    }
    public void delete(int id) {
        List<ShotRecord> records = findAll();
        records.removeIf(r -> r.id() == id);
        write(records);
    }
    private void write(List<ShotRecord> records) {
        List<String> lines = records.stream().map(r -> r.id() + ";" + r.speed()
                + ";" + r.angle() + ";" + r.range()).toList();
        try {
            Files.write(file, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Не удалось сохранить журнал", e);
        }
    }
}
