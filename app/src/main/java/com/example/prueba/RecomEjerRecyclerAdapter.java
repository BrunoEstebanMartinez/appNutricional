package com.example.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


import DataBaseEstructure.recomendacion_ejercicio_Map;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecomEjerRecyclerAdapter extends RecyclerView.Adapter<RecomEjerRecyclerAdapter.ViewHolder>{

    private ArrayList<recomendacion_ejercicio_Map> arrayList;

    public RecomEjerRecyclerAdapter (ArrayList<recomendacion_ejercicio_Map> arrayList){
        this.arrayList = arrayList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_wizard_ejercicios_database, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final recomendacion_ejercicio_Map recomendacionejercicioMap = arrayList.get(position);
        holder.nombre_RE.setText(recomendacionejercicioMap.getNombre_RE());
        holder.tipo_grado_RE.setText(recomendacionejercicioMap.getTipo_grado_RE());
        holder.calorias_RE.setText(recomendacionejercicioMap.getCalorias_RE());
        holder.MET_RE.setText(recomendacionejercicioMap.getMET_RE());
        holder.tiempo_RE.setText(recomendacionejercicioMap.getTiempo_RE());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), GlobalEjercicio.class);
                intent.putExtra("itemDetailEjercicio", recomendacionejercicioMap);
                holder.itemView.getContext().startActivity(intent);

            }
        });


    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre_RE, tipo_grado_RE, calorias_RE, MET_RE, tiempo_RE, Puenteo;
        CardView cardView;
        ViewHolder(final View itemView){
            super(itemView);
            nombre_RE = itemView.findViewById(R.id.NombreEjercicio);
            tipo_grado_RE = itemView.findViewById(R.id.tipo_grado);
            calorias_RE = itemView.findViewById(R.id.ViewCaloriasEjercicio);
            MET_RE = itemView.findViewById(R.id.ViewMET);
            tiempo_RE = itemView.findViewById(R.id.ViewTiempo);
            cardView = itemView.findViewById(R.id.Activity_Wizard_Ejercicios_Database);

        }
    }

}
