package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText buckysInput;
    TextView buckysText;
    MyDBHandler dbHandler;
    String food;
    int food_id;
    ListView myListView;
    ArrayList<Product> productArray;
    Context context;
    Product product;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        dbHandler = new MyDBHandler(this);

        buckysInput = (EditText) findViewById(R.id.buckysInput);
        buckysText = (TextView) findViewById(R.id.buckysText);

        printDatabase();
        showAdapter();
    }

    //Add a product to the database
    public void addButtonClicked(View view){
        product = new Product(buckysInput.getText().toString(), "Joeri");
        dbHandler.create(product);
        printDatabase();
    }

    //Delete items
    public void deleteButtonClicked(View view){
        String inputText = buckysInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        productArray = dbHandler.read();
        buckysInput.setText("");
        showAdapter();
    }

    public void showAdapter() {
        ListAdapter myAdapter = new CustomAdapter(this, productArray);
        myListView = (ListView) findViewById(R.id.myListView);
        assert myListView != null;

        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //food = String.valueOf(parent.getItemAtPosition(position));
                        food = productArray.get(position).get_productname();
                        food_id = productArray.get(position).get_id();

                        Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();

                        // Launching new Activity
                        Intent i = new Intent(getApplicationContext(), DescriptionActivity.class);
                        i.putExtra("todo", food);
                        i.putExtra("todo_id", food_id);
                        startActivity(i);



                    }
                }
        );
    }

}

