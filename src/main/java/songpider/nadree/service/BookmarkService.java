package songpider.nadree.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import songpider.nadree.model.BookmarkEntity;
import songpider.nadree.persistence.BookmarkRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookmarkService {

    @Autowired
    BookmarkRepository bookmarkRepository;

    public void validate(BookmarkEntity bookmarkEntity) {
        if (bookmarkEntity == null) {
            log.warn("Bookmark Entity cannot be null.");
            throw new RuntimeException("Bookmark Entity cannot be null.");
        }

        if (bookmarkEntity.getUserId() == null) {
            log.warn("Unknown user of Bookmark Entity.");
            throw new RuntimeException("Unknown user of Bookmark Entity.");
        }
    }

    public List<BookmarkEntity> create(BookmarkEntity bookmarkEntity) {
        validate(bookmarkEntity);
        bookmarkRepository.save(bookmarkEntity);

        return bookmarkRepository.findByUserId(bookmarkEntity.getUserId());
    }

    public List<BookmarkEntity> retrieve(String userId) {
        return bookmarkRepository.findByUserId(userId);
    }

    public List<BookmarkEntity> update(BookmarkEntity bookmarkEntity) {
        validate(bookmarkEntity);
        final Optional<BookmarkEntity> original = bookmarkRepository.findById(bookmarkEntity.getPlaceId());

        if (original.isPresent()) {
            final BookmarkEntity bookmark = original.get();
            bookmark.setMemo(bookmarkEntity.getMemo());
            bookmarkRepository.save(bookmark);
        }
        return retrieve(bookmarkEntity.getUserId());
    }

    public List<BookmarkEntity> delete(BookmarkEntity bookmarkEntity) {
        validate(bookmarkEntity);
        try {
            bookmarkRepository.delete(bookmarkEntity);
        } catch (Exception e) {
            log.error("error deleting Bookmark Entity ", bookmarkEntity.getPlaceId(),e);
            throw new RuntimeException("error deleting Bookmark Entity "+bookmarkEntity.getPlaceId());
        }
        return retrieve(bookmarkEntity.getUserId());

    }


}
