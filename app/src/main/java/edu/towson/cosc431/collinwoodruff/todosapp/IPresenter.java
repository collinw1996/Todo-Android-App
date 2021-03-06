package edu.towson.cosc431.collinwoodruff.todosapp;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/23/2017.
 */

public interface IPresenter {
    void deleteTodo(Todo todo);
    void launchAddTodoActivity();
    void launchEditTodo(Todo todo);
    void handleNewTodoResult(Todo todo);
    void handleEditTodoResult(Todo todo);
    List<Todo> getTodosFromModel();
    List<Todo> getActiveTodosFromModel();
    List<Todo> getCompletedTodosFromModel();
    void addNewTodo(Todo todo);
    void editOrComplete(Todo todo);
    void confirmDelete(Todo todo);
}
