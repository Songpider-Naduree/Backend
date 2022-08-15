package songpider.nadree.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import songpider.nadree.model.BookmarkEntity;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository <BookmarkEntity, String> {
    List<BookmarkEntity> findByUserId(String userId);
    Optional<BookmarkEntity> findByPlaceId(String placeId);
}
