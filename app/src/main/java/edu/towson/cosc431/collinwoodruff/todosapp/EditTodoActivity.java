package edu.towson.cosc431.collinwoodruff.todosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Date;

import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

public class EditTodoActivity extends AppCompatActivity implements View.OnClickListener, EditTodoController {

    EditText todoName;
    EditText contentsName;
    Button saveBtn;
    CheckBox isComplete;
    Todo todo;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        start();
        todo = new Todo();
    }

    public void start(){
        todoName = (EditText) findViewById(R.id.todoName);
        contentsName = (EditText)findViewById(R.id.contents);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        isComplete = (CheckBox)findViewById(R.id.isComplete);
        saveBtn.setOnClickListener(this);
        isComplete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.saveBtn:
                if(todoName.getText().toString().isEmpty()){
                    todo.setTitle("Title");
                }
                else
                    todo.setTitle(todoName.getText().toString());
                if(contentsName.getText().toString().isEmpty()){
                    todo.setContents("Contents");
                }
                else
                    todo.setContents(contentsName.getText().toString());
                todo.setDateCreated(new Date());
                todo.setComplete(todo.isComplete());
                saveTodo(todo);
                break;
            case R.id.isComplete:
                todo.setComplete(!todo.isComplete());
                break;
        }
    }
    @Override
    public void saveTodo(Todo todo) {
        Intent intent = new Intent();
        intent.putExtra("EDIT",todo);
        setResult(RESULT_OK,intent);
        finish();
    }
}
