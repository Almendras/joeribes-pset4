package com.example.joeribes.joeribes_pset4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity {
    MyDBHandler dbHandler;
    String dbString;
    Product description;
    String[] foods;
    TextView productView;
    TextView descriptionView;
    String product;
    int product_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        productView = (TextView) findViewById(R.id.myView);
        descriptionView = (TextView) findViewById(R.id.descriptionView);

        dbHandler = new MyDBHandler(this);

        Intent i = getIntent();
        // Getting attached intent data
        product = i.getStringExtra("todo");
        productView.setText(product);


        product_id = i.getIntExtra("todo_id", 0);
        description = dbHandler.descriptionToString(product_id);
        descriptionView.setText(description.get_description());



    }
}
