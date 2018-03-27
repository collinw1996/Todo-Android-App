package edu.towson.cosc431.collinwoodruff.todosapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Collin on 10/3/2017.
 */

public class Todo implements Parcelable {
    private String title;
    private String contents;
    private long dateCreated;
    private String _id;
    private boolean isComplete;

    public Todo(String title, String contents, long dateCreated, boolean isComplete){
        this.title = title;
        this.contents = contents;
        this.isComplete = isComplete;
        this.dateCreated = dateCreated;
        this._id = UUID.randomUUID().toString();
    }

    public Todo(){

    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
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

    public long getDateCreated(){
        return dateCreated;
    }

    public void setDateCreated(long dateCreated){
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
        out.writeLong(dateCreated);
        out.writeByte((byte) (isComplete ? 1 : 0)); //if isAwesome == true, byte == 1
        out.writeString(_id);
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
        dateCreated = in.readLong();
        isComplete = in.readByte() != 0; //isAwesome == true if byte != 0
        _id = in.readString();
    }
}
