package com.example.joeribes.joeribes_pset4;

import java.io.Serializable;

/**
 * Created by Joeri Bes on 25-9-2017.
 */

public class TodoItem implements Serializable{

    private int _id;
    private String todoName;
    private String description;
    private int finished;
    private int groupid;

    public TodoItem(String aTodoName, String aDescription, int groupID) {
        todoName = aTodoName;
        description = aDescription;
        groupid = groupID;
        finished = 0;

    }

    public TodoItem(String productName, String aDescription, int id, int isFinished, int groupID) {
        todoName = productName;
        description = aDescription;
        _id = id;
        finished = isFinished;
        groupid = groupID;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_groupID(int _groupID) {
        this.groupid = _groupID;
    }

    public void set_todoName(String newProductName) {
        todoName = newProductName;
    }

    public void set_description(String newDescription) {
        description = newDescription;
    }

    public int get_id() {
        return _id;
    }

    public int get_groupID() {
        return groupid;
    }

    public String get_todoName() {
        return todoName;
    }


    public String get_description() {
        return description;
    }

    public int get_finished() {
        return finished;
    }

    public void set_Finished(int finished) {
        this.finished = finished;
    }


}
