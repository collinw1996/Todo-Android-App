package edu.towson.cosc431.collinwoodruff.todosapp.database;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 11/9/2017.
 */

public interface IDataSource {
    List<Todo> getAllTodos();
    List<Todo> getTodo(String id);
    List<Todo> getActiveTodos();
    List<Todo> getCompletedTodos();
    void addTodo(Todo todo);
    void updateTodo(Todo todo);
    void deleteTodo(Todo todo);
    // TODO: 11/1/17 - Add the other CRUD methods
}
