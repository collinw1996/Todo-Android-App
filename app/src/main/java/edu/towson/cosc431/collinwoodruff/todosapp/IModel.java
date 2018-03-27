package edu.towson.cosc431.collinwoodruff.todosapp;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/23/2017.
 */

public interface IModel {
    List<Todo> getTodos();
    List<Todo> getActiveTodos();
    List<Todo> getCompletedTodos();
    void removeTodo(Todo todo);
    void addTodo(Todo todo);
    void editTodo(Todo todo);
}
