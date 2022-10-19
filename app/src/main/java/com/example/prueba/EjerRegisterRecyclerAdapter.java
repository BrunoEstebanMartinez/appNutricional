package com.example.prueba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import DataBaseEstructure.recomendacion_ejercicio_Map;
import java.util.ArrayList;

public class EjerRegisterRecyclerAdapter extends RecyclerView.Adapter<EjerRegisterRecyclerAdapter.MyViewHolder>{



    ArrayList<recomendacion_ejercicio_Map> arrayList;


    public EjerRegisterRecyclerAdapter(ArrayList<recomendacion_ejercicio_Map> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public EjerRegisterRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ejercicios_seguimiento, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         final recomendacion_ejercicio_Map MapeoEjercicio = arrayList.get(position);
        holder.nombre_RE.setText(MapeoEjercicio.getNombre_RE());
        holder.tipo_grado_RE.setText(MapeoEjercicio.getTipo_grado_RE());
        holder.calorias_RE.setText(MapeoEjercicio.getCalorias_RE());
        holder.MET_RE.setText(MapeoEjercicio.getMET_RE());
        holder.tiempo_RE.setText(MapeoEjercicio.getTiempo_RE());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nombre_RE, tipo_grado_RE, calorias_RE, MET_RE, tiempo_RE, Puenteo;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre_RE = itemView.findViewById(R.id.NombreEjercicioSeguimiento);
            tipo_grado_RE = itemView.findViewById(R.id.tipo_gradoSeguimiento);
            calorias_RE = itemView.findViewById(R.id.ViewCaloriasEjercicioSeguimiento);
            MET_RE = itemView.findViewById(R.id.ViewMETSeguimiento);
            tiempo_RE = itemView.findViewById(R.id.ViewTiempoSeguimiento);
            cardView = itemView.findViewById(R.id.Activity_Ejercicios_Seguimiento);
        }
    }
}
