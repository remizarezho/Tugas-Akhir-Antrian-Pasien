package com.example.tugasakhirantrianpasien;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.ActionMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class activity_informasi extends AppCompatActivity {
    private ImageView tomboltambah;
    FirebaseFirestore db;
    TextView txtKonten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        db = FirebaseFirestore.getInstance();
        txtKonten = findViewById(R.id.txtKonten);


        tomboltambah = findViewById(R.id.prefences);
        tomboltambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent datapasien = new Intent(activity_informasi.this, ActivityTambahInformasi.class);
                startActivity(datapasien);
            }
        });

        if (tools.getSharedPreferenceString(this, "level", "").equals("1")) {
           tomboltambah.setVisibility(View.GONE);
        }else {

        }

        getData();

    }


    @Override
    protected void onResume() {
        super.onResume();

        getData();
    }

    //membuat tulisan menjadi ganti secara otomatis
    private void getData() {

        final CollectionReference dbAkun = db.collection("informasi");

        dbAkun
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        txtKonten.setText(document.getData().get("konten").toString());
                    }

                } else {
                    Toast.makeText(activity_informasi.this, "gagal menerima data", Toast.LENGTH_SHORT).show();

                }

            }

        });

    }

};



