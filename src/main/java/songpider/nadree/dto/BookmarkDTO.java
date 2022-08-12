package songpider.nadree.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import songpider.nadree.model.BookmarkEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookmarkDTO {
    private String placeId;
    private String placeName;
    private Float x;
    private Float y;
    private String rAddress;
    private String Address;
    private String memo;

    public BookmarkDTO(final BookmarkEntity bookmarkEntity) {
        this.placeId = bookmarkEntity.getPlaceId();
        this.placeName = bookmarkEntity.getPlaceName();
        this.x = bookmarkEntity.getY();
        this.y = bookmarkEntity.getY();
        this.rAddress = bookmarkEntity.getRAddress();
        this.Address = bookmarkEntity.getAddress();
        this.memo = bookmarkEntity.getMemo();
    }

    public static BookmarkEntity toEntity(final BookmarkDTO bookmarkDTO) {
        return BookmarkEntity.builder()
                .placeId(bookmarkDTO.getPlaceId())
                .placeName(bookmarkDTO.getPlaceName())
                .x(bookmarkDTO.getX())
                .y(bookmarkDTO.getY())
                .rAddress(bookmarkDTO.getRAddress())
                .Address(bookmarkDTO.getAddress())
                .memo(bookmarkDTO.getMemo())
                .build();
    }
}
