package com.example.tugasakhirantrianpasien;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Nav_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CardView mDataPasien;
    private CardView mPendaftaran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mPendaftaran = findViewById(R.id.home_pendaftaran);
        mDataPasien = findViewById(R.id.home_data_pasien);

        if (tools.getSharedPreferenceString(this, "level", "").equals("1")) {
            navigationView.getMenu().findItem(R.id.nav_data_pasien).setVisible(false);

            mDataPasien.setVisibility(View.GONE);
        }

        mPendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pendaftaran = new Intent(Nav_Home.this, jadwal.class);
                startActivity(pendaftaran);
            }
        });
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profil) {

        } else if (id == R.id.nav_data_pasien) {

        } else if (id == R.id.nav_jadwal) {

        } else if (id == R.id.nav_poli) {

        } else if (id == R.id.nav_info) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_logout) {
            tools.setSharedPreference(Nav_Home.this,"isLogin", "0");
            Intent logout = new Intent(Nav_Home.this, LoginActivity.class);
            startActivity(logout);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
