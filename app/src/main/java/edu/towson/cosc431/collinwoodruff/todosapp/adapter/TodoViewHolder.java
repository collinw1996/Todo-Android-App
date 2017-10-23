package edu.towson.cosc431.collinwoodruff.todosapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Locale;

import edu.towson.cosc431.collinwoodruff.todosapp.IPresenter;
import edu.towson.cosc431.collinwoodruff.todosapp.MainController;
import edu.towson.cosc431.collinwoodruff.todosapp.R;
import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;

/**
 * Created by Collin on 10/10/2017.
 */

public class TodoViewHolder extends RecyclerView.ViewHolder {
    TextView todoTitle;
    TextView contentsName;
    CheckBox isComplete;
    TextView date;
    Todo todo;
    IPresenter controller;
    LinearLayout fullSong;

    public TodoViewHolder(View itemView, final IPresenter controller) {
        super(itemView);
        this.controller = controller;
        fullSong = (LinearLayout)itemView.findViewById(R.id.fullSong);
        fullSong.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                TodoViewHolder.this.controller.editOrComplete(todo);
            }

        });
        fullSong.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {
                TodoViewHolder.this.controller.confirmDelete(todo);
                return false;
            }
        });
        todoTitle = (TextView) itemView.findViewById(R.id.todoTitle);
        contentsName = (TextView)itemView.findViewById(R.id.todoContents);
        date = (TextView)itemView.findViewById(R.id.todoDate);
        isComplete = (CheckBox) itemView.findViewById(R.id.isComplete);
        isComplete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                todo.setComplete(!todo.isComplete());
            }
        });
    }

    public void bindTodo(Todo todo) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
        String simpleDate = dateFormat.format(todo.getDateCreated());
        todoTitle.setText(todo.getTitle());
        contentsName.setText(todo.getContents());
        isComplete.setChecked(todo.isComplete());
        date.setText(simpleDate);
        this.todo = todo;
    }
}

