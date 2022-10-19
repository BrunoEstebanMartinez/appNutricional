package com.example.prueba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends Activity {
    RecyclerView AdapterdeLayoutRegistroActivity;
    ArrayList<ProcesoGenero> procesoGeneros;
     GeneroRecyclerAdapter adapter;
     GridLayoutManager grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdapterdeLayoutRegistroActivity = (RecyclerView) findViewById(R.id.Genero);
        grid = new GridLayoutManager(this, 2);
        AdapterdeLayoutRegistroActivity.setLayoutManager(grid);
        adapter = new GeneroRecyclerAdapter(dataset());
        AdapterdeLayoutRegistroActivity.setAdapter(adapter);
    }


    private ArrayList<ProcesoGenero> dataset() {
        ArrayList<ProcesoGenero> genero = new ArrayList<>();
        genero.add(new ProcesoGenero("Mujer", R.drawable.woman));
        genero.add(new ProcesoGenero("Hombre", R.drawable.man));
        return genero;
    }

}
