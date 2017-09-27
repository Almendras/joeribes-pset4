package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText activityInput;
    EditText descriptionInput;
    MyDBHandler dbHandler;
    Context context;
    Activity activity;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        context = this;
        dbHandler = new MyDBHandler(this);

        // Initializing views
        activityInput = (EditText) findViewById(R.id.activityInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);

    }

    //Add an activity to the database
    public void addButtonClicked(View view){
        // Create activity with an Activity name and Description
        activity = new Activity(activityInput.getText().toString(), descriptionInput.getText().toString());
        dbHandler.create(activity);

        // // Launching new Activity
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}
