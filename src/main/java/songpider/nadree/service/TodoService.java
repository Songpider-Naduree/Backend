package songpider.nadree.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import songpider.nadree.model.TodoEntity;
import songpider.nadree.persistence.TodoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;


//    public String testService() {
//
//        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
//
//        repository.save(entity);
//
//        TodoEntity savedEntity = repository.findByIdAndDate(entity.getId(), entity.getDate()).get();
//        return savedEntity.getTitle();


    public List<TodoEntity> create(final TodoEntity entity) {
        //entity가 유효한지 검사
        validate(entity);

        //entity 데이터베이스에 저장
        repository.save(entity);
        log.info("Entity Id : {} is saved.",entity.getTodoId());

        //저장된 엔티티를 포함하는 새 리스트 리턴
        //findById 대신 findByIdAndDate 사용
        return repository.findByIdAndDate(entity.getId(), entity.getDate());

    }

    //entity 검증. validation
    private void validate(final TodoEntity entity) {
        if(entity==null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }
        if(entity.getId()==null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
        if(entity.getDate()==null) {
            log.warn("Date not selected.");
            throw new RuntimeException("Date not seleceted.");
        }
    }

    //retrieve 구현. 리스트 검색용
    public List<TodoEntity> retrieve(final String id, final LocalDate date) {
        return repository.findByIdAndDate(id, date);
    }

    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);
        final Optional<TodoEntity> original = repository.findByTodoId(entity.getTodoId());

        if(original.isPresent()) {
            final TodoEntity todo = original.get();
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            repository.save(todo);
        }
        return retrieve(entity.getId(), entity.getDate());
    }



    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);
        try {
            repository.delete(entity);
        } catch(Exception e) {
            log.error("error deleting entity ", entity.getTodoId(),e);
            throw new RuntimeException("error deleting entity"+entity.getTodoId());
        }
        return retrieve(entity.getId(), entity.getDate());
    }
}

