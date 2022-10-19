package com.example.prueba;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


import DataBaseEstructure.recomendacion_plan_Map;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DesayunoRecyclerAdapter extends RecyclerView.Adapter<DesayunoRecyclerAdapter.ViewHolder>{

    private ArrayList<recomendacion_plan_Map> arrayList;

    public DesayunoRecyclerAdapter (ArrayList<recomendacion_plan_Map> arrayList){
        this.arrayList = arrayList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_wizard_desayuno_database, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final recomendacion_plan_Map recomendacionPlanMap = arrayList.get(position);
        holder.nombre_Comida.setText(recomendacionPlanMap.getNombre_RP());
        holder.tipo.setText(recomendacionPlanMap.getTipo_RP());
        holder.numero_unidad_medida.setText(recomendacionPlanMap.getNumero_unidad_medida_RP());
        holder.concepto_RP.setText(recomendacionPlanMap.getConcepto_RP());
        holder.unidad_medida.setText(recomendacionPlanMap.getUnidad_de_medida_RP());
        holder.calorias_RP.setText(recomendacionPlanMap.getCalorias_RP());
        holder.proteinas_RP.setText(recomendacionPlanMap.getProteinas_RP());
        holder.carbohidratos_RP.setText(recomendacionPlanMap.getCarbohidrados_RP());
        holder.grasas_RP.setText(recomendacionPlanMap.getGrasas_RP());
        holder.cantidad_RP.setText(recomendacionPlanMap.getCantidad_RP());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), GlobalAlimento.class);
                intent.putExtra("itemDetail", recomendacionPlanMap);
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre_Comida, tipo, numero_unidad_medida, concepto_RP, unidad_medida, calorias_RP, carbohidratos_RP, proteinas_RP, grasas_RP, cantidad_RP;
        CardView cardView;
        ViewHolder(final View itemView){
            super(itemView);
            nombre_Comida = itemView.findViewById(R.id.NombreComidaDesayuno);
            tipo = itemView.findViewById(R.id.tipo);
            numero_unidad_medida = itemView.findViewById(R.id.numerounidadmedida);
            concepto_RP = itemView.findViewById(R.id.ViewConcepto);
            unidad_medida = itemView.findViewById(R.id.unidadmedida);
            calorias_RP = itemView.findViewById(R.id.ViewCalorias);
            carbohidratos_RP = itemView.findViewById(R.id.ViewCarbohidratos);
            proteinas_RP = itemView.findViewById(R.id.ViewProteinas);
            grasas_RP = itemView.findViewById(R.id.ViewGrasas);
            cantidad_RP = itemView.findViewById(R.id.ViewCantidad);
            cardView = itemView.findViewById(R.id.Activity_Wizard_Desayuno_Database);

        }
    }

}

