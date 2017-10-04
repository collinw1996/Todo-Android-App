package edu.towson.cosc431.collinwoodruff.todosapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Collin on 10/3/2017.
 */

public class Todo implements Serializable {
    private String title;
    private String contents;
    private Date dateCreated;
    private boolean isComplete;

    public Todo(String title, String contents, boolean isComplete, Date dateCreated){
        this.title = title;
        this.contents = contents;
        this.isComplete = isComplete;
        this.dateCreated = dateCreated;
    }

    public Todo(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Date getDateCreated(){
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated){
        this.dateCreated = dateCreated;
    }

    public void toggleComplete(){

        this.isComplete = !this.isComplete;
    }

    public String toString() {
        return this.title + ", " +
                this.contents + ", " +
                this.isComplete + ", " +
                this.dateCreated;
    }
}
