package com.shohay.shohay_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LanguageSelect extends AppCompatActivity {

    RadioButton english, bangla;
    RadioGroup group;
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);

        english = findViewById(R.id.radio_english);
        bangla = findViewById(R.id.radio_bangla);
        group = findViewById(R.id.radioGroup);
        next = findViewById(R.id.next);

        bangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.setEnabled(true);
                next.setVisibility(View.VISIBLE);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.setEnabled(true);
                next.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhoneNumber.class);
                startActivity(intent);
            }
        });
    }

}
