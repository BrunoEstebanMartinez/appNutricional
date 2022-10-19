package com.example.prueba;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.widget.TextView;

import java.util.ArrayList;

import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.Estructure_recomendacion_plan;
import DataBaseEstructure.recomendacion_plan_Map;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class transicionplan extends Activity{

    RecyclerView AdapterdeLayoutTransicionPlan;
    ArrayList<recomendacion_plan_Map> recomendacion_plan_maps;
    DesayunoRecyclerAdapter adapter;
    GridLayoutManager grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transicionplan);

        AdapterdeLayoutTransicionPlan = (RecyclerView) findViewById(R.id.Alimentos);
        grid = new GridLayoutManager(this, 1);
        AdapterdeLayoutTransicionPlan.setLayoutManager(grid);
        adapter = new DesayunoRecyclerAdapter(dataset());
        AdapterdeLayoutTransicionPlan.setAdapter(adapter);

    }


    private ArrayList<recomendacion_plan_Map> dataset() {
        ArrayList<recomendacion_plan_Map> food = new ArrayList<>();
        food.add(new recomendacion_plan_Map("Aceite de canola", "Aceites vegetales", "14.0", "g" ,"124", "0.0","0.0", "14.0","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de almendras", "Aceites vegetales", "4.5", "g" ,"40", "0.0","0.0", "4.5","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de aguacate", "Aceites vegetales", "14.0", "g" ,"124", "0.0","0.0", "14.0","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de babussú", "Aceites vegetales", "4.5", "g" ,"40", "0.0","0.0", "4.5","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Manteca de anarcado", "Aceites vegetales", "16.0", "g" ,"94", "2.8","4.4", "7.9","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de manteca de cacao", "Aceites vegetales", "13.6", "g" ,"120", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de coco", "Aceites vegetales", "4.5", "g" ,"39", "0.0","0.0", "4.5","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de semilla de lino", "Aceites vegetales", "13.6", "g" ,"120", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de semilla de uva", "Aceites vegetales", "13.6", "g" ,"120", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de mostaza", "Aceites vegetales", "14.0", "g" ,"124", "0.0","0.0", "14.0","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de maiz y canola", "Aceites vegetales", "14.0", "g" ,"124", "0.0","0.0", "14.0","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de oliva", "Aceites vegetales", "13.5", "g" ,"119", "0.0","0.0", "13.5","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de semilla de palma", "Aceites vegetales", "4.5", "g" ,"40", "0.0","0.0", "4.5","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de cacahuate", "Aceites vegetales", "4.5", "g" ,"40", "0.0","0.0", "4.5","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de semilla de amapola", "Aceites vegetales", "13.6", "g" ,"120", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de salvado de arroz", "Aceites vegetales", "13.6", "g" ,"120", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de cártamo", "Aceites vegetales", "13.6", "g" ,"120", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de girasol", "Aceites vegetales", "13.6.0", "g" ,"120", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de semilla de té", "Aceites vegetales", "13.6", "g" ,"120", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de semilla de tomate", "Aceites vegetales", "13.6", "g" ,"120", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite vegetal de semilla de palma", "Aceites vegetales", "13.6", "g" ,"117", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite vegetal", "Aceites vegetales", "13.6", "g" ,"120", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de germen de tripo", "Aceites vegetales", "13.6", "g" ,"124", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Grasa de tocino", "Aceites animales", "4.3", "g" ,"39", "0.0","0.0", "4.3","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Grasa de pollo", "Aceites animales", "12.8", "g" ,"115", "0.0","0.0", "12.8","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Grasa de pato", "Aceites animales", "12.8", "g" ,"113", "0.0","0.0", "12.8","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Sebo vacuno", "Aceites animales", "12.8", "g" ,"115", "0.0","0.0", "12.8","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Grasa de ganso", "Aceites animales", "12.8", "g" ,"115", "0.0","0.0", "12.8","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Aceite de arranque", "Aceites animales", "13.6", "g" ,"123", "0.0","0.0", "13.6","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Sebo de carnero", "Aceites animales", "12.8", "g" ,"115", "0.0","0.0", "12.8","1", "cucharada"));
        food.add(new recomendacion_plan_Map("Grasa de pavo", "Aceites animales", "12.8", "g" ,"115", "0.0","0.0", "12.8","1", "cucharada"));

        return food;
    }


}