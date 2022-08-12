package songpider.nadree.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import songpider.nadree.model.BookmarkEntity;

import java.util.List;

public interface BookmarkRepository extends JpaRepository <BookmarkEntity, String> {
    List<BookmarkEntity> findByUserId(String userId);
}
