package com.shohay.shohay_main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LanguageSelect extends AppCompatActivity {

    RadioButton english, bangla;
    RadioGroup group;
    TextView next;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);

        english = findViewById(R.id.radio_english);
        bangla = findViewById(R.id.radio_bangla);
        group = findViewById(R.id.radioGroup);
        next = findViewById(R.id.next);

        preferences = this.getApplicationContext().getSharedPreferences("language", MODE_PRIVATE);
        editor = preferences.edit();

        bangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.setEnabled(true);
                next.setVisibility(View.VISIBLE);
                lang = "bangla";
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.setEnabled(true);
                next.setVisibility(View.VISIBLE);
                lang = "english";
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LanguageSelect.this, PhoneNumber.class);
                editor.putString("lang", lang);
                editor.commit();
                Log.d("ss", "lang");

                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LanguageSelect.this, MainActivity.class));
    }

    private String lang = "";

}
