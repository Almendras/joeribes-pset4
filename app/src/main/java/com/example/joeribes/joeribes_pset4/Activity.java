package com.example.joeribes.joeribes_pset4;

import java.io.Serializable;

/**
 * Created by Joeri Bes on 25-9-2017.
 */

public class Activity implements Serializable{

    private int _id;
    private String productname;
    private String description;
    private int finished;


    public Activity(String productName, String aDescription) {
        productname = productName;
        description = aDescription;
        finished = 0;
    }

    public Activity(String productName, String aDescription, int id, int isFinished) {
        productname = productName;
        description = aDescription;
        _id = id;
        finished = isFinished;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productname(String newProductName) {
        productname = newProductName;
    }

    public void set_description(String newDescription) {
        description = newDescription;
    }

    public int get_id() {
        return _id;
    }

    public String get_productname() {
        return productname;
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
