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

import edu.towson.cosc431.collinwoodruff.todosapp.adapter.TodoAdapter;
import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

public class MainActivity extends AppCompatActivity implements ButtonController, View.OnClickListener, IView {

    private static final int ADD_TODO_REQUEST_CODE = 1;
    private static final int EDIT_TODO_REQUEST_CODE = 2;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String STATE = "STATE";

    RecyclerView recyclerView;
    Button addBtn;
    private TodoAdapter adapter;
    private Buttons_Fragment buttonFragment;
    IPresenter presenter;
    ArrayList<Todo> todo;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            presenter = new MainPresenter(this, new TodosModel());
            bindView();
        } else {
            todo = savedInstanceState.getParcelableArrayList(STATE);
            bindView();
            refresh();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE,todo);
    } // Save Instance state with key STATE

    private void bindView() {
        addBtn = (Button) findViewById(R.id.newTodo);
        addBtn.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoAdapter(presenter.getTodosFromModel(), presenter);
        recyclerView.setAdapter(adapter);
        buttonFragment = (Buttons_Fragment)getSupportFragmentManager()
                .findFragmentById(R.id.buttonFragment);
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
        Todo getTodo;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_TODO_REQUEST_CODE:
                    /*todo = (Todo) data.getParcelableExtra("TODO");
                    presenter.handleNewTodoResult(todo);
                    refresh();*/
                    Bundle bundle = data.getExtras();
                    getTodo = bundle.getParcelable("TODO");
                    presenter.handleNewTodoResult(getTodo);
                    break;
                case EDIT_TODO_REQUEST_CODE:
                    Bundle eBundle = data.getExtras();
                    getTodo = eBundle.getParcelable("EDIT");
                    position = eBundle.getInt("pos");
                    presenter.handleEditTodoResult(getTodo, position);
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
    public void launchEditTodo(int pos) {
        Intent intent = new Intent(this, EditTodoActivity.class);
        intent.putExtra("EDIT", presenter.getTodosFromModel().get(pos));
        intent.putExtra("pos",pos);
        startActivityForResult(intent, EDIT_TODO_REQUEST_CODE);
    }

    @Override
    public void handleEditTodo(Todo todo) {
        presenter.handleEditTodoResult(todo,position);
    }

    @Override
    public void completedTodos() {
        adapter.CompletedTodos();
        refresh();
    }

    @Override
    public void activeTodos() {
        adapter.ActiveTodos();
        refresh();
    }

    @Override
    public void allTodos() {
        adapter.AllTodos();
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
                        MainActivity.this.presenter.launchEditTodo(position);
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
}



