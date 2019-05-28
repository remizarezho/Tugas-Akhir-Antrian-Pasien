package com.example.tugasakhirantrianpasien;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.tugasakhirantrianpasien.model.Pelayanan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class PoliKlinik extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;
    RelativeLayout relativeRuangan;

    private ImageView btnEditPas2;

    private FirebaseFirestore db;
    List<Pelayanan> pelayananwes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poli_klinik);

        mRecyclerView = findViewById(R.id.recycleview1);
        btnEditPas2 = findViewById(R.id.prefences2);
        relativeRuangan= findViewById(R.id.relativeRuangan);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

        btnEditPas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent poliklinik = new Intent(PoliKlinik.this, DetailPoli.class);
                startActivity(poliklinik);
            }
        });

        if (tools.getSharedPreferenceString(this, "level", "").equals("1")) {
            btnEditPas2.setVisibility(View.GONE);
        }else {

        }

        db = FirebaseFirestore.getInstance();
        db.collection("poli")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            final ArrayList<String> data = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                data.add(document.getData().get("nama").toString());
                            }

                            db.collection("pelayanan")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                pelayananwes =new ArrayList<>();
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    Log.d(TAG, document.getId() + " => " + document.getData());

                                                        Pelayanan pelayanan =new Pelayanan(
                                                                document.getData().get("iddokter").toString(),
                                                                document.getData().get("namadokter").toString(),
                                                                document.getData().get("jampelayanan").toString(),
                                                                document.getData().get("poli").toString(),
                                                                document.getData().get("haripelayanan").toString()
                                                                );
                                                    pelayananwes .add(pelayanan);
                                                }

                                                mAdapter = new PoliKlinik2(data,PoliKlinik.this,relativeRuangan,pelayananwes);
                                                mRecyclerView.setAdapter(mAdapter);

                                            } else {

                                            }
                                        }
                                    });

                        } else {
                            Log.w(String.valueOf("aaa"), "Error getting documents.", task.getException());
                        } }
                });



    }
}
