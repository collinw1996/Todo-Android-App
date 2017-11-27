package edu.towson.cosc431.collinwoodruff.todosapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.towson.cosc431.collinwoodruff.todosapp.adapter.TodoAdapter;
import edu.towson.cosc431.collinwoodruff.todosapp.database.IDataSource;
import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/23/2017.
 */

public class TodosModel implements IModel{
    private final IDataSource dataSource;
    List<Todo> todoList=new ArrayList<>();

    public TodosModel(IDataSource ds) {
        dataSource = ds;
//        seedTodos();
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
        Executor executor = (Executor) Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("Assignment9", JsonDownloader.downloadJson());
                try {
                    String todo = JsonDownloader.downloadJson();
                    JSONArray jsonArray = new JSONArray(todo);
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        if (object.getString("userId").equals("1")) {
                            String title = object.getString("title");
                            boolean complete = object.getBoolean("completed");
                            Todo todo1 = new Todo();
                            todo1.setTitle(title);
                            todo1.setComplete(complete);
                            todoList.add(todo1);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return todoList;
    }

    @Override
    public List<Todo> getActiveTodos() {
        Executor executor = (Executor) Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("Assignment9", JsonDownloader.downloadJson());
                try {
                    String todo = JsonDownloader.downloadJson();
                    JSONArray jsonArray = new JSONArray(todo);
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        if (object.getString("userId").equals("1") && !object.getBoolean("completed")) {
                            String title = object.getString("title");
                            boolean complete = object.getBoolean("completed");
                            Todo todo1 = new Todo();
                            todo1.setTitle(title);
                            todo1.setComplete(complete);
                            todoList.add(todo1);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return todoList;
    }

    @Override
    public List<Todo> getCompletedTodos() {
        Executor executor = (Executor) Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("Assignment9", JsonDownloader.downloadJson());
                try {
                    String todo = JsonDownloader.downloadJson();
                    JSONArray jsonArray = new JSONArray(todo);
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        if (object.getString("userId").equals("1") && object.getBoolean("completed")) {
                            String title = object.getString("title");
                            boolean complete = object.getBoolean("completed");
                            Todo todo1 = new Todo();
                            todo1.setTitle(title);
                            todo1.setComplete(complete);
                            todoList.add(todo1);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return todoList;
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
