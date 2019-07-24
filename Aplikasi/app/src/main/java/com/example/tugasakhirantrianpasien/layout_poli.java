package com.example.tugasakhirantrianpasien;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class layout_poli extends AppCompatActivity {
    private CardView mUmum;
    private CardView mLaktasi;
    private CardView mGizi;
    private CardView mLansia;
    private CardView mImunisasi;
    private CardView mSanitasi;
    private CardView mKB;
    private CardView mGigi;

    private TextView editNamaDokter;
    private TextView editSenkam;
    private TextView editJumat;
    private TextView editSabtu;

    private FirebaseFirestore db;

    private ImageButton mTambahData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_poli);

        mUmum = findViewById(R.id.periksa_umum);
        mLaktasi = findViewById(R.id.periksa_laktasi);
        mGizi = findViewById(R.id.periksa_gizi);
        mLansia = findViewById(R.id.perikas_lansia);
        mImunisasi = findViewById(R.id.periksa_imunisasi);
        mSanitasi = findViewById(R.id.periksa_sanitasi);
        mKB = findViewById(R.id.periksa_kb);
        mGigi = findViewById(R.id.periksa_gigi);

        mTambahData = findViewById(R.id.img_tambahData);

        mTambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent poliklinik = new Intent(layout_poli.this, DetailPoli.class);
                startActivity(poliklinik);
            }
        });

        if (tools.getSharedPreferenceString(this, "level", "").equals("1")) {
            mTambahData.setVisibility(View.GONE);
        } else {

        }

        mUmum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                editNamaDokter = popupView.findViewById(R.id.edit_namaDok);
                editSenkam = popupView.findViewById(R.id.edit_hariPel);
                editJumat = popupView.findViewById(R.id.edit_hariPeljumat);
                editSabtu = popupView.findViewById(R.id.edit_hariPelSabtu);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                db = FirebaseFirestore.getInstance();
                db.collection("pelayanan")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (document.get("poli").toString().equals("Ruang Pemeriksaan Umum")) {
                                            editNamaDokter.setText(document.get("namadokter").toString());
                                            editSenkam.setText(document.get("senkam").toString());
                                            editJumat.setText(document.get("jumat").toString());
                                            editSabtu.setText(document.get("sabtu").toString());
                                        }
                                    }
                                }
                            }
                        });

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        mLaktasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                editNamaDokter = popupView.findViewById(R.id.edit_namaDok);
                editSenkam = popupView.findViewById(R.id.edit_hariPel);
                editJumat = popupView.findViewById(R.id.edit_hariPeljumat);
                editSabtu = popupView.findViewById(R.id.edit_hariPelSabtu);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                db = FirebaseFirestore.getInstance();
                db.collection("pelayanan")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (document.get("poli").toString().equals("Ruang Laktasi")) {
                                            editNamaDokter.setText(document.get("namadokter").toString());
                                            editSenkam.setText(document.get("senkam").toString());
                                            editJumat.setText(document.get("jumat").toString());
                                            editSabtu.setText(document.get("sabtu").toString());
                                        }
                                    }
                                }
                            }
                        });

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        mGizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                editNamaDokter = popupView.findViewById(R.id.edit_namaDok);
                editSenkam = popupView.findViewById(R.id.edit_hariPel);
                editJumat = popupView.findViewById(R.id.edit_hariPeljumat);
                editSabtu = popupView.findViewById(R.id.edit_hariPelSabtu);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                db = FirebaseFirestore.getInstance();
                db.collection("pelayanan")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (document.get("poli").toString().equals("Ruang Gizi")) {
                                            editNamaDokter.setText(document.get("namadokter").toString());
                                            editSenkam.setText(document.get("senkam").toString());
                                            editJumat.setText(document.get("jumat").toString());
                                            editSabtu.setText(document.get("sabtu").toString());
                                        }
                                    }
                                }
                            }
                        });

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        mLansia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                editNamaDokter = popupView.findViewById(R.id.edit_namaDok);
                editSenkam = popupView.findViewById(R.id.edit_hariPel);
                editJumat = popupView.findViewById(R.id.edit_hariPeljumat);
                editSabtu = popupView.findViewById(R.id.edit_hariPelSabtu);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                db = FirebaseFirestore.getInstance();
                db.collection("pelayanan")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (document.get("poli").toString().equals("Ruang Kes. Usia Lanjut")) {
                                            editNamaDokter.setText(document.get("namadokter").toString());
                                            editSenkam.setText(document.get("senkam").toString());
                                            editJumat.setText(document.get("jumat").toString());
                                            editSabtu.setText(document.get("sabtu").toString());
                                        }
                                    }
                                }
                            }
                        });

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        mImunisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                editNamaDokter = popupView.findViewById(R.id.edit_namaDok);
                editSenkam = popupView.findViewById(R.id.edit_hariPel);
                editJumat = popupView.findViewById(R.id.edit_hariPeljumat);
                editSabtu = popupView.findViewById(R.id.edit_hariPelSabtu);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                db = FirebaseFirestore.getInstance();
                db.collection("pelayanan")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (document.get("poli").toString().equals("Ruang Imunisasi dan MTBS")) {
                                            editNamaDokter.setText(document.get("namadokter").toString());
                                            editSenkam.setText(document.get("senkam").toString());
                                            editJumat.setText(document.get("jumat").toString());
                                            editSabtu.setText(document.get("sabtu").toString());
                                        }
                                    }
                                }
                            }
                        });

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        mSanitasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                editNamaDokter = popupView.findViewById(R.id.edit_namaDok);
                editSenkam = popupView.findViewById(R.id.edit_hariPel);
                editJumat = popupView.findViewById(R.id.edit_hariPeljumat);
                editSabtu = popupView.findViewById(R.id.edit_hariPelSabtu);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                db = FirebaseFirestore.getInstance();
                db.collection("pelayanan")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (document.get("poli").toString().equals("Ruang Sanitasi")) {
                                            editNamaDokter.setText(document.get("namadokter").toString());
                                            editSenkam.setText(document.get("senkam").toString());
                                            editJumat.setText(document.get("jumat").toString());
                                            editSabtu.setText(document.get("sabtu").toString());
                                        }
                                    }
                                }
                            }
                        });

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        mKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                editNamaDokter = popupView.findViewById(R.id.edit_namaDok);
                editSenkam = popupView.findViewById(R.id.edit_hariPel);
                editJumat = popupView.findViewById(R.id.edit_hariPeljumat);
                editSabtu = popupView.findViewById(R.id.edit_hariPelSabtu);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                db = FirebaseFirestore.getInstance();
                db.collection("pelayanan")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (document.get("poli").toString().equals("Ruang KIA dan KB")) {
                                            editNamaDokter.setText(document.get("namadokter").toString());
                                            editSenkam.setText(document.get("senkam").toString());
                                            editJumat.setText(document.get("jumat").toString());
                                            editSabtu.setText(document.get("sabtu").toString());
                                        }
                                    }
                                }
                            }
                        });

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        mGigi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                editNamaDokter = popupView.findViewById(R.id.edit_namaDok);
                editSenkam = popupView.findViewById(R.id.edit_hariPel);
                editJumat = popupView.findViewById(R.id.edit_hariPeljumat);
                editSabtu = popupView.findViewById(R.id.edit_hariPelSabtu);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                db = FirebaseFirestore.getInstance();
                db.collection("pelayanan")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (document.get("poli").toString().equals("Ruang Kesehatan Gigi dan Mulut")) {
                                            editNamaDokter.setText(document.get("namadokter").toString());
                                            editSenkam.setText(document.get("senkam").toString());
                                            editJumat.setText(document.get("jumat").toString());
                                            editSabtu.setText(document.get("sabtu").toString());
                                        }
                                    }
                                }
                            }
                        });

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }
}
