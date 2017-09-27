package com.example.joeribes.joeribes_pset4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Activity> {

    public CustomAdapter(Context context, ArrayList<Activity> activities) {
        super(context, R.layout.custom_row , activities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());

        View customView = convertView;
        if(customView == null){
            customView = myInflater.inflate(R.layout.custom_row, parent, false);
        }

        String singleFoodItem = getItem(position).get_productname();
        int idItem = getItem(position).get_id();
        TextView myText = (TextView) customView.findViewById(R.id.myTextView);
        ImageView myImage = (ImageView) customView.findViewById(R.id.imageView);

        myText.setText(singleFoodItem);
        myImage.setImageResource(R.drawable.husky);
        return customView;
    }
}
