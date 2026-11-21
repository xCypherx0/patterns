package lab14;

import java.util.List;
import java.util.Optional;
public interface ShotRepository {
    void save(ShotRecord record);
    Optional<ShotRecord> findById(int id);
    List<ShotRecord> findAll();
    void delete(int id);
}
