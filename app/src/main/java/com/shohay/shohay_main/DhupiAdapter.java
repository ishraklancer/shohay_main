package com.shohay.shohay_main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class DhupiAdapter extends ArrayAdapter {
    private Activity context;
    private List<User> dhupilist;

    public DhupiAdapter(@NonNull Activity context, List<User> dhupilist) {
        super(context, R.layout.dhupi_item, dhupilist);
        this.context = context;
        this.dhupilist = dhupilist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.dhupi_item, null, true);
        TextView name = item.findViewById(R.id.dhupi_name);
        TextView gender = item.findViewById(R.id.dhupi_gender);
        RatingBar rating = item.findViewById(R.id.dhupi_rating);
        TextView address = item.findViewById(R.id.dhupi_add);
        TextView rate = item.findViewById(R.id.dhupi_rate);


        final User user = dhupilist.get(position);

        name.setText("Name: " + user.getName());
        gender.setText("Gender:" + user.getGender());
        address.setText("Address: " + user.getAddress());
        rating.setRating(Float.valueOf(user.getRating()));
        rate.setText("Rate: " + user.getDhupi_rate());

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedData.user = user;
                context.startActivity(new Intent(context.getApplicationContext(), confirmation_map.class));
            }
        });

        /// TODO: set item info

        return item;
    }
}
