package com.example.prueba;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import DataBaseEstructure.recomendacion_plan_Map;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class GeneroRecyclerAdapter extends RecyclerView.Adapter<GeneroRecyclerAdapter.ViewHolder> {
    private ArrayList<ProcesoGenero> arrayList;


    public GeneroRecyclerAdapter (ArrayList<ProcesoGenero> arrayList){
        this.arrayList = arrayList;

    }

    @Override
    public GeneroRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_wizard_usuario_genero, parent, false);
        return new GeneroRecyclerAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final GeneroRecyclerAdapter.ViewHolder holder, int position) {
        final ProcesoGenero procesoGenero = arrayList.get(position);
        holder.Genero.setText(procesoGenero.getGenero());
        holder.ImagenGenero.setImageResource(procesoGenero.getImagengenero());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), procesoPerfilPuente.class);
                intent.putExtra("itemDetailGenero", procesoGenero);
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Genero;
        ImageView ImagenGenero;
        CardView cardView;
        ViewHolder(final View itemView){
            super(itemView);
           Genero = itemView.findViewById(R.id.TextoGenero);
           ImagenGenero = itemView.findViewById(R.id.ImagenGenero);
           cardView = itemView.findViewById(R.id.Activity_Wizard_Usuario_Genero);

        }
    }
}
