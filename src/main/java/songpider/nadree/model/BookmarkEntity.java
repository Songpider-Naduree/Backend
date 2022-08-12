package songpider.nadree.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Bookmark")
public class BookmarkEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String placeId;
    private String userId; // User 로그인 id
    private String placeName;
    private Float x;
    private Float y;
    private String rAddress; //도로명주소
    private String Address; //지번주소
    private String memo;

}