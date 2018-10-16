package com.shohay.shohay_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class confirmation_map extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText start_time, dur;
    Button confirm;
    String userName, userNumber, providerName, providerNumber, providerRate, providerRating;
    TextView t1, t2, t3, t4;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_map);

        Intent in = getIntent();


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");



        start_time = findViewById(R.id.start_time);
        dur = findViewById(R.id.duration);
        confirm = findViewById(R.id.confirm);

        t1 = findViewById(R.id.providerName);
        t2 = findViewById(R.id.providerNumber);
        t3 = findViewById(R.id.providerRate);
        t4 = findViewById(R.id.providerRating);


        userName = in.getStringExtra("userName");
        userNumber = in.getStringExtra("userNumber");
        providerName = in.getStringExtra("providerName");
        providerNumber = in.getStringExtra("providerNumber");
        providerRate = in.getStringExtra("providerRate");
        providerRating = in.getStringExtra("providerRating");

        t1.setText(providerName);
        t2.setText(providerNumber);
        t3.setText(providerRate);
        t4.setText(providerRating);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String primaryKey = myRef.push().getKey();

                //TODO: CREATE SUITABLE ORDER CLASS AND PUSH TO "orders"

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
