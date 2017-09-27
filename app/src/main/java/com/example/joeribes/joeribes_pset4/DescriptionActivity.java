package com.example.joeribes.joeribes_pset4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionActivity extends AppCompatActivity {
    MyDBHandler dbHandler;
    String dbString;
    Activity description;
    TextView activityView;
    TextView descriptionView;
    String activity;
    int activity_id;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:

                //Delete item
                dbHandler.delete(activity_id);
                Toast.makeText(DescriptionActivity.this, "Deleted activity " + activity , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        activityView = (TextView) findViewById(R.id.activityView);
        descriptionView = (TextView) findViewById(R.id.descriptionView);

        dbHandler = new MyDBHandler(this);

        // Getting attached intent data
        Intent i = getIntent();
        activity = i.getStringExtra("todo");
        activityView.setText(activity);

        activity_id = i.getIntExtra("todo_id", 0);
        description = dbHandler.descriptionToString(activity_id);
        descriptionView.setText(description.get_description());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.description, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
