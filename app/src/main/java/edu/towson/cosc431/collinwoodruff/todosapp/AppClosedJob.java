package edu.towson.cosc431.collinwoodruff.todosapp;

import android.app.Notification;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import org.json.JSONException;
import org.json.JSONObject;

import edu.towson.cosc431.collinwoodruff.todosapp.database.IDataSource;
import edu.towson.cosc431.collinwoodruff.todosapp.model.Todo;
import edu.towson.cosc431.valis.imageservice.TodosService;

/**
 * Created by Collin on 12/4/2017.
 */

public class AppClosedJob extends JobService {

    private AppClosedJob.UpdateAppsAsyncTask updateTask = new AppClosedJob.UpdateAppsAsyncTask();
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

    private class UpdateAppsAsyncTask extends AsyncTask<JobParameters, Void, JobParameters[]> {

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
            NotificationCompat.Builder builder = new NotificationCompat.Builder(AppClosedJob.this);
            builder.setSmallIcon(android.R.drawable.ic_input_add);
            builder.setContentText("New Todo was Inserted");
            builder.setContentTitle("New Todo");
            Notification notification = builder.build();
            NotificationManagerCompat.from(AppClosedJob.this).notify(1, notification);
        }
    }
}