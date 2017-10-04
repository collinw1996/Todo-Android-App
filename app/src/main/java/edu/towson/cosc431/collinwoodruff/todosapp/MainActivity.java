package edu.towson.cosc431.collinwoodruff.todosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int ADD_TODO_REQUEST_CODE = 1;
    Button newTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
    }

    public void start(){
        newTodo = (Button)findViewById(R.id.newTodo);
        newTodo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.newTodo:
                Intent intent = new Intent(this, NewTodoActivity.class);
                startActivityForResult(intent, ADD_TODO_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_TODO_REQUEST_CODE:
                    newTodo(data);
                    break;
            }
        }
    }

    public void newTodo(Intent data){
        Todo todo = (Todo)data.getSerializableExtra("TODO");
        Log.d("Tag", todo.toString());
    }
}
