package com.shohay.shohay_main;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class Registration extends AppCompatActivity {

    CheckBox serv;
    TextInputLayout m, mr, c, cr, b, br;
    boolean flag = false;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    @BindView(R.id.name)
    EditText name;

    //    @BindView(R.id.email)
    EditText email;

    EditText day;
    EditText month;

    EditText year;

    EditText house;

    EditText road;
    EditText district;

    Button register;

    private String name1;
    private String email1;
    private String phone_number1;
    private String address1;
    private String moila_rate1;
    private String gender1;
    private String dob1;
    private String rating1;
    private String dhupi_rate1;
    private String napit_rate1;

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email = findViewById(R.id.email);

        day = findViewById(R.id.day);

        month = findViewById(R.id.month);

        year = findViewById(R.id.year);

        house = findViewById(R.id.house);

        road = findViewById(R.id.road);
        district = findViewById(R.id.district);


        preferences = this.getApplicationContext().getSharedPreferences("phonenumber", MODE_PRIVATE);

//        Toast.makeText(this)
        name = findViewById(R.id.name);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        m = findViewById(R.id.moila);
        mr = findViewById(R.id.moila_r);
        c = findViewById(R.id.clean);
        cr = findViewById(R.id.clean_r);
        b = findViewById(R.id.barber);
        br = findViewById(R.id.barber_r);
        register = findViewById(R.id.signup);
        radioSexGroup = findViewById(R.id.gender);

        email = findViewById(R.id.email);


        serv = findViewById(R.id.serv_pro);
        serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == false) {
                    m.setVisibility(View.VISIBLE);
                    mr.setVisibility(View.VISIBLE);
                    c.setVisibility(View.VISIBLE);
                    cr.setVisibility(View.VISIBLE);
                    b.setVisibility(View.VISIBLE);
                    br.setVisibility(View.VISIBLE);
                } else {
                    m.setVisibility(View.GONE);
                    mr.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                    cr.setVisibility(View.GONE);
                    b.setVisibility(View.GONE);
                    br.setVisibility(View.GONE);
                }
                flag = !flag;
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name1 = name.getText().toString();
                email1 = email.getText().toString();

                String day1 = day.getText().toString();
                String month1 = month.getText().toString();
                String year1 = year.getText().toString();
                String house1 = house.getText().toString();
                String road1 = road.getText().toString();
                String district1 = district.getText().toString();

                address1 = house1 + "//" + road1 + "//" + district1;
                dob1 = day1 + "/" + month1 + "/" + year1;

                int id = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(id);
                gender1 = (String) radioSexButton.getText();

                rating1 = "-1";
                moila_rate1 = mr.getEditText().getText().toString();
                dhupi_rate1 = cr.getEditText().getText().toString();
                napit_rate1 = br.getEditText().getText().toString();

                if (moila_rate1.matches("")) {
                    moila_rate1 = "0";
                }
                if (dhupi_rate1.matches("")) {
                    dhupi_rate1 = "0";
                }
                if (napit_rate1.matches("")) {
                    napit_rate1 = "0";
                }

                phone_number1 = preferences.getString("phonenumber", "");

                String primaryKey = myRef.push().getKey();
                User user = new User(name1, email1, phone_number1, address1, moila_rate1, gender1, dob1, rating1, dhupi_rate1, napit_rate1);
                myRef.child(primaryKey).setValue(user);


                /// TODO: phone auth

//                mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//                    @Override
//                    public void onVerificationCompleted(PhoneAuthCredential credential) {
//                        // This callback will be invoked in two situations:
//                        // 1 - Instant verification. In some cases the phone number can be instantly
//                        //     verified without needing to send or enter a verification code.
//                        // 2 - Auto-retrieval. On some devices Google Play services can automatically
//                        //     detect the incoming verification SMS and perform verification without
//                        //     user action.
//                        Log.d("", "onVerificationCompleted:" + credential);
//
////                        signInWithPhoneAuthCredential(credential);
//                    }
//
//                    @Override
//                    public void onVerificationFailed(FirebaseException e) {
//                        // This callback is invoked in an invalid request for verification is made,
//                        // for instance if the the phone number format is not valid.
//                        Log.w("", "onVerificationFailed", e);
//
//                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
//                            // Invalid request
//                            // ...
//                        } else if (e instanceof FirebaseTooManyRequestsException) {
//                            // The SMS quota for the project has been exceeded
//                            // ...
//                        }
//
//                        // Show a message and update the UI
//                        // ...
//                    }
//
//                    @Override
//                    public void onCodeSent(String verificationId,
//                                           PhoneAuthProvider.ForceResendingToken token) {
//                        // The SMS verification code has been sent to the provided phone number, we
//                        // now need to ask the user to enter the code and then construct a credential
//                        // by combining the code with a verification ID.
//                        Log.d("", "onCodeSent:" + verificationId);
//
//                        // Save verification ID and resending token so we can use them later
//                        mVerificationId = verificationId;
//                        mResendToken = token;
//
//                        // ...
//                    }
//                };
//
//                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                        phone_number1,        // Phone number to verify
//                        60,                 // Timeout duration
//                        TimeUnit.SECONDS,   // Unit of timeout
//                        this,               // Activity (for callback binding)
//                        mCallbacks);        // OnVerificationStateChangedCallbacks


            }
        });
    }

    SharedPreferences preferences;


}
