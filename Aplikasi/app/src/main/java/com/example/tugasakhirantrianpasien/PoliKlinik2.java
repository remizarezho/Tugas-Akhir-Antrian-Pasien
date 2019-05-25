package com.example.tugasakhirantrianpasien;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugasakhirantrianpasien.model.Pelayanan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.support.constraint.Constraints.TAG;

class PoliKlinik2 extends RecyclerView.Adapter<PoliKlinik2.ViewHolder> {
    ArrayList<String> dataGlobal;
    Context context;
    View relativeRuangan;
    private TextView editNamaDokter;
    private TextView editJamPelayanan;
    private TextView editHariPelayanan;
    FirebaseFirestore db;
    PopupWindow popupWindow;
    List<Pelayanan> pelayananwes = new ArrayList<>();

    public PoliKlinik2(ArrayList<String> data, Context context,View relativeRuangan, List<Pelayanan> pelayananwes  ) {
        dataGlobal = data;
        this.context=context;
        this.relativeRuangan=relativeRuangan;
        this.pelayananwes=pelayananwes;


    }

    @NonNull
    @Override
    public PoliKlinik2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_poli_klinik2, parent, false);
        return new ViewHolder(mview);
        //untuk mengeset layout yang digunakan
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //mengeset masing views
        holder.textView.setText(dataGlobal.get(position));
    }

    @Override
    public int getItemCount() {
        return dataGlobal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_ruangperiksa);

            // on item click
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        String clickedDataItem = dataGlobal.get(pos);

                        boolean visible=false;

                        for (int i = 0; i <pelayananwes.size() ; i++) {
                            if (pelayananwes.get(i).getPoli().equals(clickedDataItem)) {

                                visible=true;
                                 Toast.makeText(v.getContext(), "You clicked " + clickedDataItem, Toast.LENGTH_SHORT).show();
                                onButtonShowPopupWindowClick(relativeRuangan,clickedDataItem,pelayananwes.get(i));

                                break;
                            }

                        }

                        if (!visible){
                            Toast.makeText(v.getContext(), "Tidak ada dokter", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
        }

        public void onButtonShowPopupWindowClick(final View view, String poli,Pelayanan pelayananwesPelayanans) {

            // inflate the layout of the popup window
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_window, null);

            editNamaDokter =popupView.findViewById(R.id.edit_namaDok);
            editJamPelayanan =popupView.findViewById(R.id.edit_jamPel);
            editHariPelayanan = popupView.findViewById(R.id.edit_hariPel);

            // create the popup window
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true; // lets taps outside the popup also dismiss it
             popupWindow = new PopupWindow(popupView, width, height, focusable);



                    editNamaDokter.setText(pelayananwesPelayanans.getIddokter());
                    editJamPelayanan.setText(pelayananwesPelayanans.getJampelayanan());
                    editHariPelayanan.setText(pelayananwesPelayanans.getHaripelayanan());


            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window tolken
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            // dismiss the popup window when touched
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });
        }
    }

    void setList(  List<Pelayanan> pelayananwesPelayanans){
        pelayananwes =pelayananwesPelayanans;
        notifyDataSetChanged();
    }
}

