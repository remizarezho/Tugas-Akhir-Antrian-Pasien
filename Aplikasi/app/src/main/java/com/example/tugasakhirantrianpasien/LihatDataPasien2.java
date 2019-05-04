package com.example.tugasakhirantrianpasien;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class LihatDataPasien2 extends RecyclerView.Adapter<LihatDataPasien2.ViewHolder> {
    ArrayList<String> dataGlobal;

    public LihatDataPasien2(ArrayList<String> data) {
        dataGlobal = data;
    }

    @NonNull
    @Override
    public LihatDataPasien2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_lihat_data_pasien2, parent, false);
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

        ImageView mImageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageJudul);
            textView = itemView.findViewById(R.id.tvJudul);
        }
    }
}

