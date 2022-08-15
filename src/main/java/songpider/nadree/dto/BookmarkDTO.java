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
    private String address_name;
    private String memo;

    public BookmarkDTO(final BookmarkEntity bookmarkEntity) {
        this.placeId = bookmarkEntity.getPlaceId();
        this.placeName = bookmarkEntity.getPlaceName();
        this.address_name = bookmarkEntity.getAddress_name();
        this.memo = bookmarkEntity.getMemo();
    }

    public static BookmarkEntity toEntity(final BookmarkDTO bookmarkDTO) {
        return BookmarkEntity.builder()
                .placeId(bookmarkDTO.getPlaceId())
                .placeName(bookmarkDTO.getPlaceName())
                .address_name(bookmarkDTO.getAddress_name())
                .memo(bookmarkDTO.getMemo())
                .build();
    }
}
