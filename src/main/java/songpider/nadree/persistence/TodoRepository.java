package songpider.nadree.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import songpider.nadree.model.TodoEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    List<TodoEntity> findByIdAndDate(String id, LocalDate date);

    Optional<TodoEntity> findByTodoId(String todoId);

}