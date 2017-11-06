package edu.towson.cosc431.collinwoodruff.todosapp;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/23/2017.
 */

public interface IView {
    void refresh();
    void launchNewTodo();
    void editOrComplete(Todo todo);
    void confirmDelete(Todo todo);
    void launchEditTodo(int pos);
    void handleEditTodo(Todo todo);
}
