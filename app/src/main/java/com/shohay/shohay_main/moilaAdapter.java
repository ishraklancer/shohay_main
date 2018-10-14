package com.shohay.shohay_main;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class moilaAdapter extends ArrayAdapter {

    private Activity context;
    private List<User> moilalist;

    public moilaAdapter(@NonNull Activity context, List<User> moilalist) {
        super(context, R.layout.moila_item, moilalist);
        this.context = context;
        this.moilalist = moilalist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.moila_item, null, true);
        TextView name = item.findViewById(R.id.moila_name);
        TextView gender = item.findViewById(R.id.moila_gender);
        RatingBar rating = item.findViewById(R.id.moila_rating);
        TextView address = item.findViewById(R.id.moila_add);
        TextView rate = item.findViewById(R.id.moila_rate);

        User user = moilalist.get(position);

        name.setText("Name: " + user.getName());
        gender.setText("Gender:" + user.getGender());
        address.setText("Address: " + user.getAddress());
        rating.setRating(Float.valueOf(user.getRating()));
//        rate.setText("Rate: " + user.getMoila_rate());

        /// TODO: set item info

        return item;
    }

}
