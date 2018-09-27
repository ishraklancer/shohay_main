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
                        Intent intent = new Intent(getApplicationContext(), LanguageSelect.class);
                        startActivity(intent);
                    }
                }, 1500);

            }
        }, 4500);


    }
}
