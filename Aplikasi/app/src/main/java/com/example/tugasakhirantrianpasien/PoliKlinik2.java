package com.example.tugasakhirantrianpasien;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class PoliKlinik2 extends RecyclerView.Adapter<PoliKlinik2.ViewHolder> {
    ArrayList<String> dataGlobal;

    public PoliKlinik2(ArrayList<String> data) {
        dataGlobal = data;
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
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}

