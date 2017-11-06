package edu.towson.cosc431.collinwoodruff.todosapp;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/23/2017.
 */

public interface IPresenter {
    void deleteTodo(Todo todo);
    void launchAddTodoActivity();
    void launchEditTodo(int position);
    void handleNewTodoResult(Todo todo);
    void handleEditTodoResult(Todo todo, int pos);
    List<Todo> getTodosFromModel();
    void addNewTodo(Todo todo);
    void editOrComplete(Todo todo);
    void confirmDelete(Todo todo);
}
