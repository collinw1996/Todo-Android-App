package edu.towson.cosc431.collinwoodruff.todosapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Collin on 11/6/2017.
 */

public class Buttons_Fragment extends Fragment implements View.OnClickListener {

    Button all;
    Button active;
    Button completed;
    ButtonController buttonController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buttons_fragment, container, false);
        all = (Button) view.findViewById(R.id.all);
        active = (Button) view.findViewById(R.id.active);
        completed = (Button)view.findViewById(R.id.completed);
        all.setOnClickListener(this);
        active.setOnClickListener(this);
        completed.setOnClickListener(this);
        buttonController = (ButtonController) getActivity();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all:
                buttonController.allTodos();
                break;
            case R.id.active:
                buttonController.activeTodos();
                break;
            case R.id.completed:
                buttonController.completedTodos();
                break;
        }
    }
}
