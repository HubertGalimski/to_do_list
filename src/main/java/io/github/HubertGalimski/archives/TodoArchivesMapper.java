package io.github.HubertGalimski.archives;

import io.github.HubertGalimski.todo.Todo;
import org.springframework.stereotype.Repository;

public class TodoArchivesMapper {


    public static TodoArchives toTodoArchives(Todo todo) {
        TodoArchives todoArchives = new TodoArchives();
        todoArchives.setText(todo.getText());
        return todoArchives;
    }
}
