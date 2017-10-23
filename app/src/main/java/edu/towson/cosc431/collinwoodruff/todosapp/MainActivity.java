package edu.towson.cosc431.collinwoodruff.todosapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.adapter.TodoAdapter;
import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IView {

    private static final int ADD_TODO_REQUEST_CODE = 1;
    private static final int EDIT_TODO_REQUEST_CODE = 2;
    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    Button addBtn;
    private TodoAdapter adapter;
    IPresenter presenter;
    Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this, new TodosModel());
        bindView();
    }

    private void bindView() {
        addBtn = (Button) findViewById(R.id.newTodo);
        addBtn.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoAdapter(presenter.getTodosFromModel(), presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newTodo:
                presenter.launchAddTodoActivity();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_TODO_REQUEST_CODE:
                    todo = (Todo) data.getParcelableExtra("SONG");
                    presenter.handleNewTodoResult(todo);
                    refresh();
                    break;
                case EDIT_TODO_REQUEST_CODE:
                    todo = (Todo) data.getParcelableExtra("EDIT");
                    presenter.handleEditTodoResult(todo);
                    break;
            }
        }
    }

    @Override
    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void launchNewTodo() {
        Intent intent = new Intent(this, NewTodoActivity.class);
        startActivityForResult(intent, ADD_TODO_REQUEST_CODE);
    }

    @Override
    public void launchEditTodo(Todo todo) {
        Intent intent = new Intent(this, EditTodoActivity.class);
        intent.putExtra("EDIT", todo);
        startActivityForResult(intent, EDIT_TODO_REQUEST_CODE);
    }

    public void confirmDelete(final Todo todo) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirm Delete");
        alertDialogBuilder
                .setMessage("Are you sure you want to delete?")
                .setCancelable(false)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.presenter.deleteTodo(todo);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public void editOrComplete(final Todo todo) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Edit Or Completed");
        alertDialogBuilder
                .setMessage("Would you like to edit the reminder or mark completed?")
                .setCancelable(false)
                .setPositiveButton("Completed", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        todo.setComplete(!todo.isComplete());
                        MainActivity.this.refresh();
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.presenter.launchEditTodo(todo);
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
}
    /*public static final int ADD_TODO_REQUEST_CODE = 1;
    public static final int EDIT_TODO_REQUEST_CODE = 2;
    Button newTodo;

    private TodoAdapter adapter;
    List<Todo> todoList;
    RecyclerView recyclerView;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoList = new ArrayList<>();
        addTodos();
        start();
    }

    private void addTodos() {
        todoList.add(new Todo("Trash", "Today", true, new Date("07/18/2017")));
        /*todoList.add(new Todo("Dishes", "Tomorrow", false, new Date("10/11/2017")));
        todoList.add(new Todo("Run", "12 Minutes", false, new Date("10/08/2017")));
        todoList.add(new Todo("Walk Dog", "1 hour", true, new Date("06/18/2017")));
        todoList.add(new Todo("Feed Dog", "1/3 Cup", false, new Date("10/11/2017")));
        todoList.add(new Todo("Trash", "Today", true, new Date("07/18/2017")));
        todoList.add(new Todo("Dishes", "Tomorrow", false, new Date("10/11/2017")));
        todoList.add(new Todo("Run", "12 Minutes", false, new Date("10/08/2017")));
        todoList.add(new Todo("Walk Dog", "1 hour", true, new Date("06/18/2017")));
        todoList.add(new Todo("Feed Dog", "1/3 Cup", false, new Date("10/11/2017")));
    }

    public void start() {
        newTodo = (Button) findViewById(R.id.newTodo);
        newTodo.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoAdapter(todoList, this);
        recyclerView.setAdapter(adapter);
    }

    public void refresh(){
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newTodo:
                Intent intent = new Intent(this, NewTodoActivity.class);
                startActivityForResult(intent, ADD_TODO_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_TODO_REQUEST_CODE:
                    newTodo(data);
                    break;
                case EDIT_TODO_REQUEST_CODE:
                    saveTodo(data);
                    break;
            }
        }
    }

    public void newTodo(Intent data) {
        Todo todo = (Todo) data.getParcelableExtra("TODO");
        todoList.add(new Todo(todo.getTitle(), todo.getContents(), todo.isComplete(), todo.getDateCreated()));
        refresh();
    }

    public void editTodo(Todo todo) {
        Intent intent = new Intent(this, EditTodoActivity.class);
        startActivityForResult(intent, EDIT_TODO_REQUEST_CODE);
        position = todoList.indexOf(todo);
    }

    private void saveTodo(Intent data) {
        Todo todo = (Todo) data.getParcelableExtra("EDIT");
        todoList.set(position, new Todo(todo.getTitle(), todo.getContents(), todo.isComplete(), todo.getDateCreated()));
        refresh();
    }

    public void delete(Todo todo) {
        todoList.remove(todo);
        refresh();
    }

    public void confirmDelete(final Todo todo) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirm Delete");
        alertDialogBuilder
                .setMessage("Are you sure you want to delete?")
                .setCancelable(false)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.delete(todo);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public void editOrComplete(final Todo todo) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Edit Or Completed");
        alertDialogBuilder
                .setMessage("Would you like to edit the reminder or mark completed?")
                .setCancelable(false)
                .setPositiveButton("Completed", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        todo.setComplete(!todo.isComplete());
                        MainActivity.this.refresh();
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.editTodo(todo);
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
}*/


