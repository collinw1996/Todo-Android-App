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
    Intent iExtras;
    Bundle editInfo;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        todo = new Todo();
        start();
        iExtras = getIntent();
        editInfo = iExtras.getExtras();

        if (editInfo != null) {
            todo = editInfo.getParcelable("EDIT");
            todoName.setText(todo.getTitle());
            contentsName.setText(todo.getContents());
            isComplete.setChecked(todo.isComplete());
        }
    }

    public void start(){
        todoName = (EditText) findViewById(R.id.todoName);
        contentsName = (EditText)findViewById(R.id.contents);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        isComplete = (CheckBox)findViewById(R.id.isComplete);

        todoName.setText(todo.getTitle());
        contentsName.setText(todo.getContents());
        isComplete.setChecked(todo.isComplete());

        saveBtn.setOnClickListener(this);
        isComplete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.saveBtn:
                saveTodo(todo);
                break;
            case R.id.isComplete:
                todo.setComplete(!todo.isComplete());
                break;
        }
    }
    @Override
    public void saveTodo(Todo todo) {
        int position = 0;

        if(editInfo != null) {
            position = editInfo.getInt("pos");
        }
        Intent intent = new Intent();
        todo.setContents(todoName.getText().toString());
        todo.setContents(contentsName.getText().toString());
        todo.setDateCreated(System.currentTimeMillis());
        todo.setComplete(todo.isComplete());

        intent.putExtra("EDIT",todo);
        intent.putExtra("pos", position);
        setResult(RESULT_OK,intent);
        finish();
    }
}
