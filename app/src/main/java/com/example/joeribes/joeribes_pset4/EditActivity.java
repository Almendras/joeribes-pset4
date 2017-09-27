package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    EditText activityInput;
    EditText descriptionInput;
    MyDBHandler dbHandler;
    Context context;
    Activity activity;
    String activityName;
    int activity_id;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        context = this;
        dbHandler = new MyDBHandler(this);

        Intent i = getIntent();
        activityName = i.getStringExtra("todos");
        activity_id = i.getIntExtra("todos_id", 0);

        // Initializing views
        activityInput = (EditText) findViewById(R.id.activityInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);

        activityInput.setText(activityName);;
        activity = dbHandler.readDescription(activity_id);
        descriptionInput.setText(activity.get_description());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Add an activity to the database
    public void editButtonClicked(View view){
        // Edit activity with an Activity name and Description
        activity.set_activityName(activityInput.getText().toString());
        activity.set_description(descriptionInput.getText().toString());

        dbHandler.update(activity);

        Toast.makeText(EditActivity.this, "The activity has been changed" , Toast.LENGTH_LONG).show();

        // Launching new Activity
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();

    }
}
