package songpider.nadree.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import songpider.nadree.model.TodoEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data


public class TodoDTO {
    private String todoId;
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean done;

    public TodoDTO(final TodoEntity entity) {
        this.todoId = entity.getTodoId();
        this.title = entity.getTitle();
        this.date=entity.getDate();
        this.done = entity.isDone();
    }

    //controller에서 DTO를 Entity로 변환하기 위한 toEntity함수
    public static TodoEntity toEntity(final TodoDTO dto) {
        return TodoEntity.builder()
                .todoId(dto.getTodoId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .date(dto.getDate())
                .build();
    }

}