package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {

    ListView activityListView;
    TodoManager dbHandler;
    Context context;
    String groupName;
    ArrayList<TodoList> todoListsContainer;
    ArrayList<TodoItem> todoList;
    String activity;
    int activity_id;
    int activity_finished;
    int count = -1;
    int counter = 0;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteDialog();
                return true;

            case R.id.action_add:
                Intent intent3 = new Intent(getBaseContext(), AddActivity.class);
                intent3.putExtra("todoList", groupName);
                startActivity(intent3);
                finish();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        context = this;

        dbHandler = TodoManager.getsInstance(this);

        Intent i = getIntent();
        groupName = i.getStringExtra("groupName");

        todoListsContainer = dbHandler.read();

        for(TodoList todoList1 : todoListsContainer) {
            if (todoList1.getGroup().equals(groupName)) {
                count = counter;
            }
            counter++;

        }

        todoList = todoListsContainer.get(count).getTodoItemList();
        showAdapter();
    }




    // Show the listView adapter
    public void showAdapter() {
        ListAdapter myAdapter = new CustomAdapter(this, todoList);
        activityListView = (ListView) findViewById(R.id.activityListView);
        assert activityListView != null;

        activityListView.setAdapter(myAdapter);

        activityListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        // Receive the todoItem and id
                        activity = todoList.get(position).get_todoName();
                        activity_id = todoList.get(position).get_id();
                        activity_finished = todoList.get(position).get_finished();

                        // Launching new TodoItem
                        Intent i = new Intent(getApplicationContext(), DescriptionActivity.class);
                        i.putExtra("todo", activity);
                        i.putExtra("todo_id", activity_id);
                        i.putExtra("activity_finished", activity_finished);
                        startActivity(i);
                    }
                }
        );
    }


    // Create a delete dialog if the user agrees to delete his/her todoItem
    public void deleteDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(TodoListActivity.this);
        alert.setTitle("Delete todo List");
        alert.setMessage("Are you sure you want to delete the todo List " + groupName + "?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Delete item
                dbHandler.deleteList(groupName);
                Toast.makeText(TodoListActivity.this, "Deleted todo List " + groupName , Toast.LENGTH_LONG).show();

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
