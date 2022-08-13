package songpider.nadree.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Todo")

public class TodoEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String todoId;
    private String id;
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean done;
}
