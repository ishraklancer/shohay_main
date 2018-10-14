package com.shohay.shohay_main;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;

public class ProviderRegistration extends AppCompatActivity {


    final int PICK_IMAGE_REQUEST = 71;

    CheckBox serv;
    TextInputLayout m, mr, c, cr, b, br;
    boolean flag = false;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    private SearchView searcher;

    @BindView(R.id.name)
    EditText name;

    //    @BindView(R.id.email)
    EditText email;

    Button location;

    Button register;



    private String name1;
    private String email1;
    private String phone_number1;
    private String address1;
    private String moila_rate1;
    private String gender1;
    private String dob = "";
    private int day;
    private int month;
    private int year;
    private double lat = -1.1, lang = -1.1;
    Double rate;
    String serviceType;


    FirebaseDatabase database;
    DatabaseReference myRef;

    Button bday;

    Spinner spinner;


    EditText etRate;


    List<Address> locations;

    DatePicker datePicker;
    Calendar calendar;


    LocationManager lm;
    Location loc;
    LocationListener locationListener;

    Button choose;


    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            dob = new StringBuilder().append(i).append("/").append(i1 + 1).append("/").append(i2).toString();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, onDateSetListener, year, month, day);
        }
        return null;
    }

    Uri filepath;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                propic.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    ImageView propic;

    void uploadImage() {
        if (filepath != null) {
            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(ProviderRegistration.this, "hoyeche", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private static final int LOCATION_REQUEST = 500;

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }

        spinner = (Spinner) findViewById(R.id.serviceType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.serviceTypeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        propic = findViewById(R.id.propic);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("DPs");

        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        choose = findViewById(R.id.choose);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);
            }
        });


        email = findViewById(R.id.email);

        etRate = findViewById(R.id.rate);


        preferences = this.getApplicationContext().getSharedPreferences("phonenumber", MODE_PRIVATE);

        location = findViewById(R.id.getloc);
        bday = findViewById(R.id.bday);

        bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
            }
        });

        loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lang = location.getLongitude();
                if (lat != -1.1 && lang != -1.1) {
                    Toast.makeText(ProviderRegistration.this, "paise", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, locationListener);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        name = findViewById(R.id.name);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        register = findViewById(R.id.signup);
        radioSexGroup = findViewById(R.id.gender);

        email = findViewById(R.id.email);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(ProviderRegistration.this);
                progressDialog.setTitle("Registering");
                progressDialog.show();

                uploadImage();

                name1 = name.getText().toString();
                email1 = email.getText().toString();

                serviceType = spinner.getSelectedItem().toString();

                rate = Double.parseDouble(etRate.getText().toString());


                int id = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(id);
                gender1 = (String) radioSexButton.getText();



                phone_number1 = preferences.getString("phonenumber", "");

                String primaryKey = myRef.push().getKey();
                try {
                    if (lang != -1.1 && lat != -1.1) {
                        Geocoder geocoder;
                        List<Address> addresses;
                        geocoder = new Geocoder(ProviderRegistration.this, Locale.getDefault());
                        addresses = geocoder.getFromLocation(lat, lang, 1);
                        address1 = addresses.get(0).getAddressLine(0);

                    } else {
                        Toast.makeText(ProviderRegistration.this, "painai :(", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                User user = new User(name1, email1, phone_number1, address1, moila_rate1, gender1, dob1, rating1, dhupi_rate1, napit_rate1);
                ProviderClass providerClass = new ProviderClass(name1, email1, phone_number1, address1, lat, lang, gender1, dob, "0", serviceType, rate);
                myRef.child(primaryKey).setValue(providerClass);

                progressDialog.dismiss();
                startActivity(new Intent(ProviderRegistration.this, NavigationHome.class));
                finish();

            }
        });
    }

    SharedPreferences preferences;


}
