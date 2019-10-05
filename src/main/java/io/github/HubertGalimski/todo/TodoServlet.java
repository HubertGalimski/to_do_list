package io.github.HubertGalimski.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
class TodoServlet {
    private final Logger logger = LoggerFactory.getLogger(TodoServlet.class);

    private TodoRepository repository;

    TodoServlet(TodoRepository repository) {
        this.repository = repository;
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

    @Transactional
    @DeleteMapping("")
    ResponseEntity<List<Todo>> deleteSelected() {
        List<Todo> list = repository.findAll().stream().map(t -> {
                    if (t.isDone()) {
                        repository.delete(t);
                        return t;
                    }
                    return null;
                }
        ).filter(Objects::nonNull).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

}

