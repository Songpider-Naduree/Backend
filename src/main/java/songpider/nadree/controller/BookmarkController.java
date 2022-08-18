package songpider.nadree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import songpider.nadree.dto.BookmarkDTO;
import songpider.nadree.dto.ResponseDTO;
import songpider.nadree.model.BookmarkEntity;
import songpider.nadree.service.BookmarkService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    @Autowired
    private BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<?> createBookmark(@AuthenticationPrincipal String userId, @RequestBody BookmarkDTO dto) {
        try {
            BookmarkEntity entity = BookmarkDTO.toEntity(dto);
            entity.setPlaceId(null);
            entity.setUserId(userId);
            List<BookmarkEntity> entities = bookmarkService.create(entity);

            List<BookmarkDTO> dtos = entities.stream().map(BookmarkDTO::new).collect(Collectors.toList());
            ResponseDTO<BookmarkDTO> response = ResponseDTO.<BookmarkDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<BookmarkDTO> response = ResponseDTO.<BookmarkDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveBookmark(@AuthenticationPrincipal String userId, @RequestBody BookmarkDTO dto) {
        List<BookmarkEntity> entities = bookmarkService.retrieve(userId);
        List<BookmarkDTO> dtos = entities.stream().map(BookmarkDTO::new).collect(Collectors.toList());
        ResponseDTO<BookmarkDTO> response = ResponseDTO.<BookmarkDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateBookmark(@AuthenticationPrincipal String userId, @RequestBody BookmarkDTO dto) {
        BookmarkEntity entity = BookmarkDTO.toEntity(dto);
        entity.setUserId(userId);
        List<BookmarkEntity> entities = bookmarkService.update(entity);
        List<BookmarkDTO> dtos = entities.stream().map(BookmarkDTO::new).collect(Collectors.toList());
        ResponseDTO<BookmarkDTO> response = ResponseDTO.<BookmarkDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBookmark(@AuthenticationPrincipal String userId, @RequestBody BookmarkDTO dto) {
        try {
            BookmarkEntity entity = BookmarkDTO.toEntity(dto);
            entity.setUserId(userId);
            List<BookmarkEntity> entities = bookmarkService.delete(entity);
            List<BookmarkDTO> dtos = entities.stream().map(BookmarkDTO::new).collect(Collectors.toList());
            ResponseDTO<BookmarkDTO> response = ResponseDTO.<BookmarkDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<BookmarkDTO> response = ResponseDTO.<BookmarkDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }

    }
}
