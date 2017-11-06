package edu.towson.cosc431.collinwoodruff.todosapp;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/23/2017.
 */

public class MainPresenter implements IPresenter{
    private final IView view;
    private final IModel model;

    public MainPresenter(IView view, IModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handleNewTodoResult(Todo todo) {
        addNewTodo(todo);
    }

    @Override
    public List<Todo> getTodosFromModel() {
        return model.getTodos();
    }

    @Override
    public void addNewTodo(Todo todo) {
        model.addTodo(todo);
    }

    @Override
    public void handleEditTodoResult(Todo todo, int pos) {
        model.getTodos().set(pos,todo);
        view.refresh();
    }

    @Override
    public void editOrComplete(Todo todo) {
        view.editOrComplete(todo);
    }

    @Override
    public void confirmDelete(Todo todo) {
        view.confirmDelete(todo);
    }

    @Override
    public void deleteTodo(Todo todo) {
        model.removeTodo(todo);
        view.refresh();
    }

    @Override
    public void launchAddTodoActivity() {
        view.launchNewTodo();
    }

    @Override
    public void launchEditTodo(int pos) {
        view.launchEditTodo(pos);
    }
}
