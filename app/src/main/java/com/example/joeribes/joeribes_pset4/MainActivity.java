package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    MyDBHandler dbHandler;
    String activity;
    int activity_id;
    int activity_finished;
    ListView activityListView;
    ArrayList<Activity> activityArray;
    Context context;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(getBaseContext(),AddActivity.class);
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
        setContentView(R.layout.activity_main);

        context = this;
        dbHandler = new MyDBHandler(this);

        printDatabase();
        showAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Print the database
    public void printDatabase(){
        activityArray = dbHandler.read();
        showAdapter();
    }

    // Show the listView adapter
    public void showAdapter() {
        ListAdapter myAdapter = new CustomAdapter(this, activityArray);
        activityListView = (ListView) findViewById(R.id.activityListView);
        assert activityListView != null;

        activityListView.setAdapter(myAdapter);

        activityListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        // Receive the activity and id
                        activity = activityArray.get(position).get_productname();
                        activity_id = activityArray.get(position).get_id();
                        activity_finished = activityArray.get(position).get_finished();

                        // Launching new Activity
                        Intent i = new Intent(getApplicationContext(), DescriptionActivity.class);
                        i.putExtra("todo", activity);
                        i.putExtra("todo_id", activity_id);
                        i.putExtra("activity_finished", activity_finished);
                        startActivity(i);
                    }
                }
        );
    }

}

