package com.example.tugasakhirantrianpasien;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugasakhirantrianpasien.model.Akun;
import com.example.tugasakhirantrianpasien.model.NomorAntrianModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.BreakIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class jadwal extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnLihatNomor;
    Button btnjadkem;
    Button btnlanjut;
    Spinner spinner;
    TextView date1;
    private int year, month, day;

    private FirebaseFirestore db;
    boolean cekSekali=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        btnjadkem = findViewById(R.id.btnjadkem);
        btnlanjut = findViewById(R.id.btnjadi);
        spinner = findViewById(R.id.spinner1);
        date1 = findViewById(R.id.date1);
        btnLihatNomor = findViewById(R.id.btnLihatNomor);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });
        btnLihatNomor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent datapasien = new Intent(jadwal.this, nomor_antrian2.class);
                startActivity(datapasien);

            }
        });
        btnjadkem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (date1.getText().toString().isEmpty()){
                    Toast.makeText(jadwal.this, "Tanggal belum ditentukan!", Toast.LENGTH_SHORT).show();
                    return;
                }


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(date1.getText().toString());


//                Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.


                        if (!cekSekali){
                            cekSekali =true;


                        Log.d("aaa", "aaa");
                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                        final Intent lanjut = new Intent(jadwal.this, nomor_antrian.class);
                        int nomor= (int) (dataSnapshot.getChildrenCount());

                        if(nomor==0){
                            nomor=1;
                        }else {
                            nomor++;
                        }

                        //////////////////////

                            db = FirebaseFirestore.getInstance();
                            final int finalNomor = nomor;
                            db.collection("limit")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {

                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    Log.d(String.valueOf("bbb"), document.getId() + " => " + document.getData());
                                                 if ( finalNomor <= (Integer.parseInt(document.getData().get("nomor").toString())) ){

                                                     DatabaseReference myRef = database.getReference(date1.getText().toString()+"/"
                                                             +tools.getSharedPreferenceString(jadwal.this, "nik", ""));

                                                     NomorAntrianModel nomorAntrianModel = new NomorAntrianModel(tools.getSharedPreferenceString
                                                             (jadwal.this, "nik", ""),
                                                             String.valueOf(finalNomor),
                                                             tools.getSharedPreferenceString(jadwal.this, "nama", ""),
                                                             spinner.getSelectedItem().toString(),
                                                             date1.getText().toString(), false
                                                     );

                                                     lanjut.putExtra("poli", spinner.getSelectedItem().toString());
                                                     lanjut.putExtra("waktu", date1.getText().toString());
                                                     lanjut.putExtra("nomor",  String.valueOf(finalNomor));

                                                     myRef.setValue(nomorAntrianModel);

                                                     startActivity(lanjut);
                                                     finish();

                                                     break;
                                                 }else {
                                                     Toast.makeText(jadwal.this, "Kuota Sudah Penuh, Silahkan Tentukan Jadwal Lain", Toast.LENGTH_SHORT).show();
                                                 }

                                                }

                                            } else {
                                                Log.w(String.valueOf("aaa"), "Error getting documents.", task.getException());
                                            } }
                                    });




                        ////////////////////////////


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                        cekSekali =false;

                    }
                });

            }
        });

        db = FirebaseFirestore.getInstance();
        db.collection("poli")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> data = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(String.valueOf("bbb"), document.getId() + " => " + document.getData());
                                    data.add(document.getData().get("nama").toString());
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(jadwal.this,
                                        android.R.layout.simple_list_item_1, android.R.id.text1, data);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(adapter);
                                spinner.setOnItemSelectedListener(jadwal.this);
                            }
                        } else {
                            Log.w(String.valueOf("aaa"), "Error getting documents.", task.getException());
                        } }
                });
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            day = selectedDay;
            month = selectedMonth;
            year = selectedYear;
            String date = (selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);

            SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy", Locale.ENGLISH);
            Date newDate = null;
            try {
                newDate = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //pengaturan output tanggal di layout jadwal
            SimpleDateFormat format2= null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                format2 = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.forLanguageTag("in"));
            }
            String s = format2.format(newDate);
            date1.setText(s);

        }
    };
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         String text = parent.getItemAtPosition(position).toString();
         Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
