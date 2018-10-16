package com.shohay.shohay_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class confirmation_map extends AppCompatActivity {

    private GoogleMap mMap;
    EditText start_time, dur;
    Button confirm;
    String userName, userNumber, providerName, providerNumber, providerRate, providerRating;
    TextView name, phoneNumber, rate;
    EditText startHour, finishHour, workDate;
    RatingBar rating;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_map);

        Intent in = getIntent();


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");


        confirm = findViewById(R.id.confirm);

        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        rate = findViewById(R.id.rate);
        startHour = findViewById(R.id.startHour);
        finishHour = findViewById(R.id.finishHour);
        workDate = findViewById(R.id.workDate);
        rating = findViewById(R.id.rating);


        userName = in.getStringExtra("userName");
        userNumber = in.getStringExtra("userNumber");
        providerName = in.getStringExtra("providerName");
        providerNumber = in.getStringExtra("providerNumber");
        providerRate = in.getStringExtra("providerRate");
        providerRating = in.getStringExtra("providerRating");

        name.setText(providerName);
        phoneNumber.setText(providerNumber);
        rate.setText(providerRate);
        rating.setRating(Float.valueOf(providerRating));


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String startHour1 = startHour.getText().toString();
                final String finishHour1 = finishHour.getText().toString();
                final String workDate1 = workDate.getText().toString();

                order = new Order(userName, userNumber, providerName, providerNumber, providerRate, providerRating, startHour1, finishHour1, workDate1, "pending");
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("orders").child("users").child(userNumber);
                reference.child(reference.push().getKey()).setValue(order);
                reference = FirebaseDatabase.getInstance().getReference("orders").child("providers").child("dhupi").child(providerNumber);
                reference.child(reference.push().getKey()).setValue(order);
                confirm.setClickable(false);
                confirm.setFocusable(false);
                Toast.makeText(confirmation_map.this, "Order Placed", Toast.LENGTH_SHORT).show();
                // String primaryKey = myRef.push().getKey();

                //TODO: CREATE SUITABLE ORDER CLASS AND PUSH TO "orders"

            }
        });
    }


}
