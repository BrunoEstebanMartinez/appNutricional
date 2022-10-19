package com.example.prueba;

import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import DataBaseEstructure.recomendacion_ejercicio_Map;

public class transicionejercicios extends Activity {

    RecyclerView AdapterdeLayoutTransicionEjercicios;
    RecomEjerRecyclerAdapter adapter;
    GridLayoutManager grid;
    String Almacena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transicionejercicios);
        AdapterdeLayoutTransicionEjercicios = (RecyclerView) findViewById(R.id.Ejercicios);
        grid = new GridLayoutManager(this, 1);
        AdapterdeLayoutTransicionEjercicios.setLayoutManager(grid);
        adapter = new RecomEjerRecyclerAdapter(dataset());
        AdapterdeLayoutTransicionEjercicios.setAdapter(adapter);




    }


    private ArrayList<recomendacion_ejercicio_Map> dataset() {
        ArrayList<recomendacion_ejercicio_Map> ejercicio = new ArrayList<>();
        ejercicio.add(new recomendacion_ejercicio_Map("Ciclismo en montaña","Moderado","","14.0","30"));
        return ejercicio;

}
}