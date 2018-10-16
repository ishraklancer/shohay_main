package com.shohay.shohay_main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DhupiAdapter extends ArrayAdapter {
    private Activity context;
    private List<ProviderClass> dhupilist;
    FirebaseUser user;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    User user1;

    SharedPreferences preferences;

    private String providerName, providerNumber, providerGender, providerRating, userName, userNumber;
    private Double providerRate;

    public DhupiAdapter(@NonNull Activity context, List<ProviderClass> dhupilist) {
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

        preferences = this.getContext().getSharedPreferences("phonenumber", MODE_PRIVATE);
        userNumber = preferences.getString("phonenumber", "");

        Toast.makeText(this.getContext(), "phonnumber", Toast.LENGTH_SHORT).show();



        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");




        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                user1 = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        final ProviderClass user = dhupilist.get(position);

        name.setText("Name: " + user.getName());
        gender.setText("Gender:" + user.getGender());
        address.setText("Address: " + user.getAddress());
        rating.setRating(Float.valueOf(user.getRating()));
        rate.setText("Rate: " + user.getRate());



        userName = user1.getName();
        userNumber = user1.getPhone_number();
        providerName = user.getName();
        providerRating = user.getRating();
        providerGender = user.getGender();
        providerRate = user.getRate();




        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context.getApplicationContext(), confirmation_map.class);
                intent.putExtra("userName", userName);
                intent.putExtra("userNumber", userNumber);
                intent.putExtra("providerName", providerName);
                intent.putExtra("providerRating", providerRating);
                intent.putExtra("providerRate", providerRate);


                context.startActivity(intent);
            }
        });

        /// TODO: set item info

        return item;
    }
}
