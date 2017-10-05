package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText activityInput;
    EditText descriptionInput;
    TodoManager dbHandler;
    Context context;
    TodoItem todoItem;
    String todoList;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        context = this;
        //dbHandler = new TodoManager(this);
        dbHandler = TodoManager.getsInstance(context);

        // Initializing views
        activityInput = (EditText) findViewById(R.id.listInput);
        descriptionInput = (EditText) findViewById(R.id.descriptionInput);

        Intent i = getIntent();
        todoList = i.getStringExtra("todoList");

    }

    //Add an todoItem to the database
    public void addButtonClicked(View view){
        // Create todoItem with an TodoItem name and Description
        todoItem = new TodoItem(activityInput.getText().toString(), descriptionInput.getText().toString(), todoList);
        dbHandler.create(todoItem);

        // // Launching new TodoItem
        Intent intent = new Intent(getBaseContext(), TodoListActivity.class);
        intent.putExtra("groupName", todoList);
        startActivity(intent);
        finish();
    }



}
