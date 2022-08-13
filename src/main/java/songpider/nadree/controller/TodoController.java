package songpider.nadree.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import songpider.nadree.dto.ResponseDTO;
import songpider.nadree.dto.TodoDTO;
import songpider.nadree.model.TodoEntity;
import songpider.nadree.service.TodoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate temporaryDate;

    @Autowired
    private TodoService service;

    /*
    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String str = service.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);

    }*/

    @PostMapping("/new")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryId = "temporary-user";
            //사용자로부터 받은 TodoDTO를 TodoEntity로 변환
            TodoEntity entity = TodoDTO.toEntity(dto);

            //todoId를 null로 초기화 (todo생성할 땐 todoId가 없어야해서)
            entity.setTodoId(null);

            //임시 사용자 아이디 설정
            entity.setId(temporaryId);
            entity.setDate(temporaryDate);


            //service를 이용해 TodoEntity 생성
            List<TodoEntity> entities = service.create(entity);

            //java의 stream()을 이용해 리턴된 entity리스트들을 TodoDTO 리스트로 변환
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            //변환된 TodoDTO리스트를 이용해 ResponseDTO 초기화
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            //ResponseDTO 리턴
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    //todo 검색
    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String temporaryId = "temporary-user";

        //service의 retrieve()함수를 사용해 todo리스트 가져오기
        List<TodoEntity> entities = service.retrieve(temporaryId, temporaryDate);
        //java stream을 이용해 리턴된 entity 리스트를 todoDTO 리스트로 변환
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
        String temporaryId = "temporary-user";
        TodoEntity entity = TodoDTO.toEntity(dto);
        entity.setId(temporaryId);
        List<TodoEntity> entities = service.update(entity);
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
        String temporaryId = "temporary-user";
        TodoEntity entity = TodoDTO.toEntity(dto);
        entity.setId(temporaryId);
        List<TodoEntity> entities = service.delete(entity);
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
        return ResponseEntity.badRequest().body(response);

    }
}