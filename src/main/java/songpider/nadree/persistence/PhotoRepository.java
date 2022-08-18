package songpider.nadree.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import songpider.nadree.model.PhotoEntity;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, String> {
    List<PhotoEntity> findByIdAndPDate(String photoId, LocalDate pDate);

}
