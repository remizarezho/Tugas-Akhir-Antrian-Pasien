package com.example.tugasakhirantrianpasien;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class informasi_adapter extends RecyclerView.Adapter<informasi_adapter.ViewHolder> {
    ArrayList<String> dataGlobal;

    public informasi_adapter(ArrayList<String> dataGlobal) {
        this.dataGlobal = dataGlobal;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mview = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.activity_informasi_adapter, viewGroup, false);
        return new ViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.konten.setText(dataGlobal.get(i));
    }

    @Override
    public int getItemCount() {
        return dataGlobal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView konten;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            konten = itemView.findViewById(R.id.tvKonten);

        }
    }
}
