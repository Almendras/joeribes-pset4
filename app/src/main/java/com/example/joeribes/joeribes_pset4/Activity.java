package com.example.joeribes.joeribes_pset4;

import java.io.Serializable;

/**
 * Created by Joeri Bes on 25-9-2017.
 */

public class Activity implements Serializable{

    private int _id;
    private String productname;
    private String description;


    public Activity(String productName, String aDescription) {
        productname = productName;
        description = aDescription;
    }

    public Activity(String productName, String aDescription, int id) {
        productname = productName;
        description = aDescription;
        _id = id;
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


}
