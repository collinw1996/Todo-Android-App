package edu.towson.cosc431.collinwoodruff.todosapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Collin on 10/3/2017.
 */

public class Todo implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(contents);
        out.writeLong(dateCreated.getTime());
        out.writeByte((byte) (isComplete ? 1 : 0)); //if isAwesome == true, byte == 1
    }

    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    private Todo(Parcel in) {
        title = in.readString();
        contents = in.readString();
        dateCreated = new Date(in.readLong());
        isComplete = in.readByte() != 0; //isAwesome == true if byte != 0
    }
}
