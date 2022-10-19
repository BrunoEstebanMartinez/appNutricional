package com.example.prueba;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import DataBaseEstructure.DataBaseOpenHelper;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import DataBaseEstructure.recomendacion_plan_Map;

public class  AlimentosRecyclerAdapter extends RecyclerView.Adapter<AlimentosRecyclerAdapter.MyViewHolder>{


    ArrayList<recomendacion_plan_Map> arrayList;

    public AlimentosRecyclerAdapter(ArrayList<recomendacion_plan_Map> arrayList) {
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_alimentos_seguimiento, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
      final recomendacion_plan_Map MapeoRecomendacionPlan = arrayList.get(position);
        holder.nombre_ComidaSeguimiento.setText(MapeoRecomendacionPlan.getNombre_RP());
        holder.tipoSeguimiento.setText(MapeoRecomendacionPlan.getTipo_RP());
        holder.numero_unidad_medidaSeguimiento.setText(MapeoRecomendacionPlan.getNumero_unidad_medida_RP());
        holder.concepto_RPSeguimiento.setText(MapeoRecomendacionPlan.getConcepto_RP());
        holder.unidad_medidaSeguimiento.setText(MapeoRecomendacionPlan.getUnidad_de_medida_RP());
        holder.calorias_RPSeguimiento.setText(MapeoRecomendacionPlan.getCalorias_RP());
        holder.proteinas_RPSeguimiento.setText(MapeoRecomendacionPlan.getProteinas_RP());
        holder.carbohidratos_RPSeguimiento.setText(MapeoRecomendacionPlan.getCarbohidrados_RP());
        holder.grasas_RPSeguimiento.setText(MapeoRecomendacionPlan.getGrasas_RP());
        holder.cantidad_RPSeguimiento.setText(MapeoRecomendacionPlan.getCantidad_RP());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        TextView nombre_ComidaSeguimiento, tipoSeguimiento, numero_unidad_medidaSeguimiento,
                concepto_RPSeguimiento, unidad_medidaSeguimiento, calorias_RPSeguimiento, carbohidratos_RPSeguimiento,
                proteinas_RPSeguimiento, grasas_RPSeguimiento, cantidad_RPSeguimiento;
        CardView cardView;

        public MyViewHolder(final View itemView){
            super(itemView);
            nombre_ComidaSeguimiento = itemView.findViewById(R.id.NombreComidaDesayunoSegumiento);
            tipoSeguimiento = itemView.findViewById(R.id.tipoSegumiento);
            numero_unidad_medidaSeguimiento = itemView.findViewById(R.id.numerounidadmedidaSegumiento);
            concepto_RPSeguimiento = itemView.findViewById(R.id.ViewConceptoSegumiento);
            unidad_medidaSeguimiento = itemView.findViewById(R.id.unidadmedidaSegumiento);
            calorias_RPSeguimiento = itemView.findViewById(R.id.ViewCaloriasSegumiento);
            carbohidratos_RPSeguimiento = itemView.findViewById(R.id.ViewCarbohidratosSegumiento);
            proteinas_RPSeguimiento = itemView.findViewById(R.id.ViewProteinasSegumiento);
            grasas_RPSeguimiento = itemView.findViewById(R.id.ViewGrasasSegumiento);
            cantidad_RPSeguimiento = itemView.findViewById(R.id.ViewCantidadSegumiento);
            cardView = itemView.findViewById(R.id.Activity_Alimentos_Seguimiento);

        }
    }
}
