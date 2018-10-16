package com.shohay.shohay_main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneNumber extends AppCompatActivity {


    EditText phoneInput, codeInput;
    Button submit, verify;

    FirebaseAuth mAuth;

    String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        Toast.makeText(getApplicationContext(),
                "TAG0", Toast.LENGTH_LONG).show();


        preferences = this.getApplicationContext().getSharedPreferences("phonenumber", MODE_PRIVATE);
        editor = preferences.edit();

        Toast.makeText(getApplicationContext(),
                "TAG1", Toast.LENGTH_LONG).show();

        mAuth = FirebaseAuth.getInstance();

        phoneInput = findViewById(R.id.phone_number);
        codeInput = findViewById(R.id.code);
        submit = findViewById(R.id.submitButton);
        verify = findViewById(R.id.verifyButton);

        Toast.makeText(getApplicationContext(),
                "TAG2", Toast.LENGTH_LONG).show();
//        editor.putString("phonenumber", phoneInput.getText().toString());
//        editor.commit();
//        startActivity(new Intent(PhoneNumber.this, Registration.class));

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignInCode();
            }
        });

    }

    private void sendVerificationCode() {
        Log.d("ishrak", "tag1");

        String phone = phoneInput.getText().toString();

        if (phone.isEmpty()) {
            phoneInput.setError("Phone number is required");
            phoneInput.requestFocus();
            return;
        }

        if (phone.length() < 14) {
            phoneInput.setError("Please enter a valid phone");
            phoneInput.requestFocus();
            return;
        }

        Toast.makeText(getApplicationContext(),
                "TAG3", Toast.LENGTH_LONG).show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }


    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }
    };

    private void verifySignInCode() {
        String code = codeInput.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //here you can open new activity
                            Toast.makeText(getApplicationContext(),
                                    "Login Successfull", Toast.LENGTH_LONG).show();


                            String phoneNumber = phoneInput.toString().trim();
                            Intent intent = new Intent(PhoneNumber.this, Registration.class);
                            editor.putString("phonenumber", phoneInput.getText().toString());
                            editor.commit();


                            startActivity(intent);
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(),
                                        "Incorrect Verification Code ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
}
