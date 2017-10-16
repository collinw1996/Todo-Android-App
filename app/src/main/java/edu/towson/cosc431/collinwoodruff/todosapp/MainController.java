package edu.towson.cosc431.collinwoodruff.todosapp;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/10/2017.
 */

public interface MainController {
    void editTodo(Todo todo);
    void delete(Todo todo);
    void confirmDelete(Todo todo);
    void editOrComplete(Todo todo);
}
