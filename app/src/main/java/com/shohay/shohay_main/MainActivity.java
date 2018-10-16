package com.shohay.shohay_main;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView tick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);
        tick = findViewById(R.id.tick);
        tick.setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setEnabled(false);
                tick.setVisibility(ImageView.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                            startActivity(new Intent(MainActivity.this, NavigationHome.class));
                            finish();
                            return;
                        }
                        Intent intent = new Intent(MainActivity.this, LanguageSelect.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1500);

            }
        }, 4500);


    }
}
