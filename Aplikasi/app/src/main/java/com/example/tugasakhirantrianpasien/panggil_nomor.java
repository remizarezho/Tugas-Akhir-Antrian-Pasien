package com.example.tugasakhirantrianpasien;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugasakhirantrianpasien.model.NomorAntrianModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class panggil_nomor extends AppCompatActivity {
    private TextView mNomorAntrian;
    private LinearLayout mplayBtn;
    private LinearLayout mnextBtn;
    private TextToSpeech textToSpeech;

    String date="";
    NomorAntrianModel  model ;
    FirebaseDatabase database;

    @Override
    public void onBackPressed() {
        textToSpeech.stop();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panggil_nomor);

        mNomorAntrian = findViewById(R.id.nomor_antrian);
        mplayBtn = findViewById(R.id.btn_play);
        mnextBtn = findViewById(R.id.btn_next);
        database = FirebaseDatabase.getInstance();
        getNomorAntrian();



        SimpleDateFormat format2= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            format2 = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.forLanguageTag("in"));
        }
        date= format2.format(new Date());


        if(textToSpeech ==null){
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = 0;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ttsLang = textToSpeech.setLanguage(Locale.forLanguageTag("in-ID"));
                    }
                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        }



        mplayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "panggilan nomor antrian " + mNomorAntrian.getText().toString();
                Log.i("TTS", "button clicked: " + data);
                int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_ADD, null);

                if (speechStatus == TextToSpeech.ERROR) {
                    Log.e("TTS", "Error in converting Text to Speech!");
                }
            }
        });

        mnextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference(date+"/"+
                        model.getNomor()+"-" +   model.getNik());

                NomorAntrianModel nomorAntrianModel = new NomorAntrianModel(
                        model.getNik(),
                        String.valueOf(model.getNomor()),
                        model.getNama(),
                        model.getPoli(),
                        model.getWaktu(), true
                );

                myRef.setValue(nomorAntrianModel, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError,
                                                   @NonNull DatabaseReference databaseReference) {

                                ////
                                getNomorAntrian();
                            }
                        }
                );
            }
        });
    }

    boolean isfound=false;

    private void getNomorAntrian(){
        isfound=false;
        DatabaseReference myRef = database.getReference(date);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    for (DataSnapshot snapshot:  userSnapshot.getChildren()) {
                        Log.d("aaa", "onDataChange: ");
                        if (snapshot.getChildrenCount() > 0) {
                            if (snapshot.child("status").getValue().toString().equals("false")) {
                                isfound = true;
                                model = new NomorAntrianModel(
                                        snapshot.child("nik").getValue().toString(),
                                        snapshot.child("nomor").getValue().toString(),
                                        snapshot.child("nama").getValue().toString(),
                                        snapshot.child("poli").getValue().toString(),
                                        snapshot.child("waktu").getValue().toString(),
                                        Boolean.getBoolean(snapshot.child("status").getValue().toString())
                                );
                                mNomorAntrian.setText(model.getNomor());

                                mplayBtn.performClick();
                                break;
                            }

                        }
                    }
                }

                if (!isfound){
                    Toast.makeText(panggil_nomor.this, "Batas maksimal antrian", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }
}