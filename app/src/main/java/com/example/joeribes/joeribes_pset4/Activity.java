package com.example.joeribes.joeribes_pset4;

import java.io.Serializable;

/**
 * Created by Joeri Bes on 25-9-2017.
 */

public class Activity implements Serializable{

    private int _id;
    private String activityName;
    private String description;
    private int finished;


    public Activity(String anActivityName, String aDescription) {
        activityName = anActivityName;
        description = aDescription;
        finished = 0;
    }

    public Activity(String productName, String aDescription, int id, int isFinished) {
        activityName = productName;
        description = aDescription;
        _id = id;
        finished = isFinished;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_activityName(String newProductName) {
        activityName = newProductName;
    }

    public void set_description(String newDescription) {
        description = newDescription;
    }

    public int get_id() {
        return _id;
    }

    public String get_activityName() {
        return activityName;
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
