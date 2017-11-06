package edu.towson.cosc431.collinwoodruff.todosapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/23/2017.
 */

public class TodosModel implements IModel{
    List<Todo> todos;
    int position;

    public TodosModel() {
        todos = new ArrayList<>();
        makeSongs();
    }

    private void makeSongs() {
        todos.add(new Todo("Trash", "Today", true, new Date("07/18/2017")));
        todos.add(new Todo("Dishes", "Tomorrow", false, new Date("10/11/2017")));
        todos.add(new Todo("Run", "12 Minutes", false, new Date("10/08/2017")));
        todos.add(new Todo("Walk Dog", "1 hour", true, new Date("06/18/2017")));
        todos.add(new Todo("Feed Dog", "1/3 Cup", false, new Date("10/11/2017")));
        todos.add(new Todo("Trash", "Today", true, new Date("07/18/2017")));
        todos.add(new Todo("Dishes", "Tomorrow", false, new Date("10/11/2017")));
        todos.add(new Todo("Run", "12 Minutes", false, new Date("10/08/2017")));
        todos.add(new Todo("Walk Dog", "1 hour", true, new Date("06/18/2017")));
        todos.add(new Todo("Feed Dog", "1/3 Cup", false, new Date("10/11/2017")));
    }

    @Override
    public List<Todo> getTodos() {
        return todos;
    }

    @Override
    public void removeTodo(Todo todo) {
        todos.remove(todo);
    }

    @Override
    public void addTodo(Todo todo) {
        todos.add(todo);
    }

}
