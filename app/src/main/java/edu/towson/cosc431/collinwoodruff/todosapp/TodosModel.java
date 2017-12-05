package edu.towson.cosc431.collinwoodruff.todosapp;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.database.IDataSource;
import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/23/2017.
 */

public class TodosModel implements IModel{
    private final IDataSource dataSource;

    public TodosModel(IDataSource ds) {
        dataSource = ds;
        //seedTodos();
    }

    public void seedTodos(){
        dataSource.addTodo(new Todo("Trash", "Take out", System.currentTimeMillis(), false));
        dataSource.addTodo(new Todo("Dog", "Walk dog", System.currentTimeMillis(), false));
        dataSource.addTodo(new Todo("Dishes", "Wash dishes", System.currentTimeMillis(),true));
        dataSource.addTodo(new Todo("Soccer", "Soccer practice", System.currentTimeMillis(), true));
        dataSource.addTodo(new Todo("Website", "Finish website", System.currentTimeMillis(), false));
        dataSource.addTodo(new Todo("Internship", "Apply!!!", System.currentTimeMillis(), true));
        dataSource.addTodo(new Todo("Job", "Remeber Check", System.currentTimeMillis(), false));
        dataSource.addTodo(new Todo("Cat", "Feed cat", System.currentTimeMillis(), false));
        dataSource.addTodo(new Todo("Kitchen", "Clean floor", System.currentTimeMillis(), true));
        dataSource.addTodo(new Todo("Car", "Oil change", System.currentTimeMillis(), true));
    }

    @Override
    public List<Todo> getTodos() {
        return dataSource.getAllTodos();
    }

    @Override
    public List<Todo> getActiveTodos() {
        return dataSource.getActiveTodos();
    }

    @Override
    public List<Todo> getCompletedTodos() {
        return dataSource.getCompletedTodos();
    }

    @Override
    public void removeTodo(Todo todo) {
        dataSource.deleteTodo(todo);
    }

    @Override
    public void addTodo(Todo todo) {
        dataSource.addTodo(todo);
    }

    @Override
    public void editTodo(Todo todo) {
        dataSource.updateTodo(todo);
    }
}
