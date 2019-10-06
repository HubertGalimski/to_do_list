package io.github.HubertGalimski.todo;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TodoService {
    TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    List<Todo> deleteAllByDone() {
        return todoRepository.deleteAllByDone(true);
    }
}
