package com.shohay.shohay_main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProviderNavigationHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;

    TextView head_name, head_phone;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = this.getApplicationContext().getSharedPreferences("phonenumber", MODE_PRIVATE);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View lala = navigationView.getHeaderView(0);

        head_name = lala.findViewById(R.id.head_name);
        head_phone = lala.findViewById(R.id.head_phoneNumber);

        head_phone.setText(preferences.getString("phonenumber", ""));
        head_name.setText("alauddin");


        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new HomeFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dhupi) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new DhupiFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Dhupi");
        } else if (id == R.id.nav_history) {

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new HistoryFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("History");

        } else if (id == R.id.nav_home) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new HomeFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Home");

        } else if (id == R.id.nav_napit) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new NapitFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Napit");
        } else if (id == R.id.nav_notifications) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new NotificationFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Notifications");
        } else if (id == R.id.nav_moylaman) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new DirtyFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("MoylaMan");

        } else if (id == R.id.nav_orders) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new OrderFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Pending Orders");
        } else if (id == R.id.nav_logout) {
            SharedPreferences preferences = this.getApplicationContext().getSharedPreferences("language", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            String lang = preferences.getString("lang", "");
            if (lang.matches("")) {
                startActivity(new Intent(ProviderNavigationHome.this, LanguageSelect.class));
            } else {
                startActivity(new Intent(ProviderNavigationHome.this, PhoneNumber.class));
            }

        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(ProviderNavigationHome.this, SettingsActivity.class));
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(ProviderNavigationHome.this, Profile.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(ProviderNavigationHome.this, About.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
