package io.github.HubertGalimski.archives;

import io.github.HubertGalimski.todo.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/archives")
public class TodoArchivesServlet {

    private final Logger logger = LoggerFactory.getLogger(TodoArchivesServlet.class);

    private TodoArchivesRepository repository;

    public TodoArchivesServlet(TodoArchivesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<TodoArchives>> findAllTodosArchives() {
        logger.info("Got request");
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    ResponseEntity<TodoArchives> saveTodo(@RequestBody Todo todo) {
        TodoArchives todoArchives = new TodoArchivesMapper().toTodoArchives(todo);
        todoArchives = repository.save(todoArchives);
        return ResponseEntity.ok(todoArchives);
    }
}
