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
    TodoManager dbHandler;
    Context context;
    TodoItem todoItem;

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
        //dbHandler = new TodoManager(this);
        dbHandler = TodoManager.getsInstance(context);

        // Initializing views
        activityInput = (EditText) findViewById(R.id.activityInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);

    }

    //Add an todoItem to the database
    public void addButtonClicked(View view){
        // Create todoItem with an TodoItem name and Description
        int test = 0;
        todoItem = new TodoItem(activityInput.getText().toString(), descriptionInput.getText().toString(), test);
        dbHandler.create(todoItem);

        // // Launching new TodoItem
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }



}
