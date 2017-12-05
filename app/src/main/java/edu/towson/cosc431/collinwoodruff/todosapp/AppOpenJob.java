package edu.towson.cosc431.collinwoodruff.todosapp;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.todosapp.database.IDataSource;
import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;
import edu.towson.cosc431.valis.imageservice.TodosService;

/**
 * Created by Collin on 12/4/2017.
 */

public class AppOpenJob extends JobService {

    private UpdateAppsAsyncTask updateTask = new UpdateAppsAsyncTask();
    private final TodosService service = TodosService.getInstance(3000);
    IDataSource ds;

    @Override
    public boolean onStartJob(JobParameters params) {
        updateTask.execute(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    private class UpdateAppsAsyncTask extends AsyncTask<JobParameters, Void, JobParameters[]>{

        @Override
        protected JobParameters[] doInBackground(JobParameters... jobParameters) {
            String todoList = service.getTodos();
            try {
                Todo todo = new Todo();
                JSONObject object = new JSONObject(todoList);
                todo.setTitle(object.getString("title"));
                todo.setContents(object.getString("body"));
                ds.addTodo(todo);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new JobParameters[0];
        }

        @Override
        protected void onPostExecute(JobParameters[] jobParameters) {
            super.onPostExecute(jobParameters);
            Toast.makeText(AppOpenJob.this, "New Todo Inserted Into Database", Toast.LENGTH_SHORT).show();
        }
    }
}
