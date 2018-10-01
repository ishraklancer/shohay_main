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

public class napit_adapter extends ArrayAdapter {

    private Activity context;
    private List<User> napitlist;

    public napit_adapter(@NonNull Activity context, List<User> napitlist) {
        super(context, R.layout.napit_item, napitlist);
        this.context = context;
        this.napitlist = napitlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.napit_item, null, true);
        TextView name = item.findViewById(R.id.napit_name);
        TextView gender = item.findViewById(R.id.napit_gender);
        RatingBar rating = item.findViewById(R.id.napit_rating);
        TextView address = item.findViewById(R.id.napit_add);
        TextView rate = item.findViewById(R.id.napit_rate);

        User user = napitlist.get(position);

        name.setText("Name: " + user.getName());
        gender.setText("Gender:" + user.getGender());
        address.setText("Address: " + user.getAddress());
        rating.setRating(Float.valueOf(user.getRating()));
        rate.setText("Rate: " + user.getNapit_rate());

        /// TODO: set item info

        return item;
    }
}
