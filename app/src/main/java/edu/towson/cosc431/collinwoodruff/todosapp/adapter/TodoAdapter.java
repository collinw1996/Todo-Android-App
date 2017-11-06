package edu.towson.cosc431.collinwoodruff.todosapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.IPresenter;
import edu.towson.cosc431.collinwoodruff.todosapp.MainController;
import edu.towson.cosc431.collinwoodruff.todosapp.R;
import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/10/2017.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    List<Todo> todoList;
    List<Todo> filter;
    IPresenter controller;

    public TodoAdapter(List<Todo> todoList, IPresenter controller){
        this.todoList = todoList;
        this.controller = controller;
        filter = new ArrayList<>();
        filter.addAll(this.todoList);
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo, parent, false);
        TodoViewHolder viewHolder = new TodoViewHolder(view, controller);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo todo = filter.get(position);
        holder.bindTodo(todo);
    }

    public void AllTodos(){
        filter.clear();
        for(Todo todo : todoList) {
            filter.add(todo);
        }
    }

    public void ActiveTodos(){
        filter.clear();
        for(Todo todo : todoList) {
            if(!todo.isComplete()) {
                filter.add(todo);
            }
        }
    }

    public void CompletedTodos(){
        filter.clear();
        for(Todo todo : todoList) {
            if(todo.isComplete()) {
                filter.add(todo);
            }
        }
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
