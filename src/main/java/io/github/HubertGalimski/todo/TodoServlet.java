package io.github.HubertGalimski.todo;

import io.github.HubertGalimski.archives.TodoArchives;
import io.github.HubertGalimski.archives.TodoArchivesMapper;
import io.github.HubertGalimski.archives.TodoArchivesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
class TodoServlet {
    private final Logger logger = LoggerFactory.getLogger(TodoServlet.class);

    private TodoRepository repository;
    private TodoArchivesRepository todoArchivesRepository;
    private TodoService todoService;

    public TodoServlet(TodoRepository repository, TodoArchivesRepository todoArchivesRepository, TodoService todoService) {
        this.repository = repository;
        this.todoArchivesRepository = todoArchivesRepository;
        this.todoService = todoService;
    }

    @GetMapping
    ResponseEntity<List<Todo>> findAllTodos() {
        logger.info("Got request");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<Todo> toggleTodo(@PathVariable Integer id) {
        var todo = repository.findById(id);
        todo.ifPresent(t -> {
            t.setDone(!t.isDone());
            repository.save(t);
        });
        return todo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(repository.save(todo));
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Todo> deleteToDo(@PathVariable Integer id) {
        Optional<Todo> toDo = repository.findById(id);
        toDo.ifPresent(t -> repository.delete(t));
        return toDo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }


    @DeleteMapping("")
    ResponseEntity<List<Todo>> deleteSelected() {
        return ResponseEntity.ok(todoService.deleteAllByDone());
    }
}

