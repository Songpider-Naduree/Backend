package songpider.nadree.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Photo")

public class PhotoEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String photoId;

    private String todoId;
    private String userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pDate;
    private String photoName;
    private String path;
    private boolean done;
}
