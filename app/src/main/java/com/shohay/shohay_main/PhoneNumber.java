package com.shohay.shohay_main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneNumber extends AppCompatActivity {

    EditText editText;
    TextView next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ss", "start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        preferences = this.getApplicationContext().getSharedPreferences("phonenumber", MODE_PRIVATE);
        editor = preferences.edit();

        editText = findViewById(R.id.phone_number);
        next = findViewById(R.id.next);


        final TextWatcher mTextEditorWatcher = new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String num = editText.getText().toString();
                if (num.length() == 14)
                    next.setVisibility(View.VISIBLE);
                else
                    next.setVisibility(View.INVISIBLE);
            }

            public void afterTextChanged(Editable s) {
            }
        };
        editText.addTextChangedListener(mTextEditorWatcher);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editText.toString().trim();
                Intent intent = new Intent(PhoneNumber.this, Registration.class);
                editor.putString("phonenumber", editText.getText().toString());
                editor.commit();


                startActivity(intent);
            }
        });
    }

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
}
