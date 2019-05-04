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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.BreakIterator;
import java.util.ArrayList;


public class jadwal extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    Button btnjadkem;
    Button btnlanjut;
    Spinner spinner;
    TextView date1;
    private int year, month, day;
    private BreakIterator datePickerButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        btnjadkem = findViewById(R.id.btnjadkem);
        btnlanjut = findViewById(R.id.btnjadi);
        spinner = findViewById(R.id.spinner1);
        date1 = findViewById(R.id.date1);

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });
        btnjadkem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(jadwal.this, Nav_Home.class);
                startActivity(kembali);
            }
        });

        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lanjut = new Intent(jadwal.this, nomor_antrian.class);
                String nomor= "1";
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(date1.getText().toString());

                NomorAntrianModel nomorAntrianModel = new NomorAntrianModel();
                nomorAntrianModel.setId(date1.getText().toString()+tools.getSharedPreferenceString(jadwal.this, "nama", ""));
                nomorAntrianModel.setDetail(tools.getSharedPreferenceString(jadwal.this, "nik", ""),
                nomor,
                tools.getSharedPreferenceString(jadwal.this, "nama", ""),
                spinner.getSelectedItem().toString(),
                date1.getText().toString());


                lanjut.putExtra("poli", spinner.getSelectedItem().toString());
                lanjut.putExtra("waktu", date1.getText().toString());
                lanjut.putExtra("nomor", nomor);


                myRef.setValue(nomorAntrianModel);

//                Read from the database
//                myRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // This method is called once with the initial value and again
//                        // whenever data at this location is updated.
//                        String value = dataSnapshot.getValue(String.class);
//                        Log.d(TAG, "Value is: " + value);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError error) {
//                        // Failed to read value
//                        Log.w(TAG, "Failed to read value.", error.toException());
//                    }
//                });

                startActivity(lanjut);
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
//                                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(jadwal.this, data, android.R.layout.simple_spinner_item);
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
            date1.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
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
