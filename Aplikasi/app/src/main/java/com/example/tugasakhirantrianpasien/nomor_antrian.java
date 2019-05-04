package com.example.tugasakhirantrianpasien;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class nomor_antrian extends AppCompatActivity {
    private TextView rngPeriksa;
    private TextView nmrAntrian;
    private TextView noKtp;
    private TextView namaPasien;
    private TextView waktu;

    String poli;
    String nomor;
    String nik;
    String nmPasien;
    String wkt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomor_antrian);
        poli = getIntent().getStringExtra("poli");
        wkt = getIntent().getStringExtra("waktu");

        rngPeriksa = findViewById(R.id.tv_ruangperiksa);
        nmrAntrian = findViewById(R.id.tv_nomorAntrian);
        noKtp = findViewById(R.id.tv_noKtp);
        namaPasien = findViewById(R.id.tv_namaPasien);
        waktu = findViewById(R.id.tv_waktu);

        rngPeriksa.setText(poli);
        waktu.setText(wkt);
        namaPasien.setText(tools.getSharedPreferenceString(this, "nama", ""));
        noKtp.setText(tools.getSharedPreferenceString(this, "nik", ""));
        nmrAntrian.setText(getIntent().getStringExtra("nomor"));

    }
}
