package com.example.tugasakhirantrianpasien;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PoliKlinik extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poli_klinik);

        mRecyclerView = findViewById(R.id.recycleview1);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);


        db = FirebaseFirestore.getInstance();
        db.collection("poli")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> data = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                data.add(document.getData().get("nama").toString());
                            }

                            mAdapter = new PoliKlinik2(data);
                            mRecyclerView.setAdapter(mAdapter);

                        } else {
                            Log.w(String.valueOf("aaa"), "Error getting documents.", task.getException());
                        } }
                });
    }
}
