package com.example.joeribes.joeribes_pset4;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionActivity extends AppCompatActivity {

    TodoManager dbHandler;
    TodoItem description;
    TextView activityView;
    TextView descriptionView;
    String activity;
    int activity_id;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intent1 = new Intent(getBaseContext(), EditActivity.class);
                intent1.putExtra("todos", activity);
                intent1.putExtra("todos_id", activity_id);
                startActivity(intent1);
                finish();
                return true;

            case R.id.action_delete:
                deleteDialog();
                return true;
            case R.id.action_home:
                Intent intent2 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent2);
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

        //dbHandler = new TodoManager(this);
        dbHandler = TodoManager.getsInstance(this);

        // Getting attached intent data
        Intent i = getIntent();
        activity = i.getStringExtra("todo");
        activity_id = i.getIntExtra("todo_id", 0);

        // Set the todoItem name and description in the textViews
        activityView.setText(activity);
        description = dbHandler.readDescription(activity_id);
        descriptionView.setText(description.get_description());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.description, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Create a delete dialog if the user agrees to delete his/her todoItem
    public void deleteDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(DescriptionActivity.this);
        alert.setTitle("Delete todoItem");
        alert.setMessage("Are you sure you want to delete " + activity + "?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Delete item
                dbHandler.delete(activity_id);
                Toast.makeText(DescriptionActivity.this, "Deleted todoItem " + activity , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }


}
