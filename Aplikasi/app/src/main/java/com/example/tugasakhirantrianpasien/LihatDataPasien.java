package com.example.tugasakhirantrianpasien;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LihatDataPasien extends AppCompatActivity {
    private ImageView btnEditPas;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data_pasien);
        mRecyclerView = findViewById(R.id.recycleview1);
        btnEditPas = findViewById(R.id.prefences);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

        btnEditPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent poliklinik = new Intent(LihatDataPasien.this, tambah_data_pasien.class);
                startActivity(poliklinik);
            }
        });

        db = FirebaseFirestore.getInstance();
        db.collection("akun")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> data = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(String.valueOf("bbb"), document.getId() + " => " + document.getData());
                                if (document.getData().get("level").toString().equals("1")){
                                    data.add(document.getData().get("nama").toString());
                                }
                            }

                            mAdapter = new LihatDataPasien2(data);
                            mRecyclerView.setAdapter(mAdapter);

                        } else {
                            Log.w(String.valueOf("aaa"), "Error getting documents.", task.getException());
                        } }
                });

    }

}
