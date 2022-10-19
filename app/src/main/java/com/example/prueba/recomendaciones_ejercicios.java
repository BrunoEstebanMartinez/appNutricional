package com.example.prueba;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.Estructure_recomendacion_ejercicios;
import DataBaseEstructure.recomendacion_ejercicio_Map;

public class recomendaciones_ejercicios extends Activity {

    private TextView mTextView;
    DataBaseOpenHelper dataBaseOpenHelper;
    RecyclerView AdapterRecomendacionEjercicioSeguimientoVista;
    EjerRegisterRecyclerAdapter adapter;
    GridLayoutManager grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones_ejercicios);
        dataBaseOpenHelper = new DataBaseOpenHelper(this);

        AdapterRecomendacionEjercicioSeguimientoVista = (RecyclerView) findViewById(R.id.EjerciciosRegistrados);
        grid = new GridLayoutManager(this, 1);
        AdapterRecomendacionEjercicioSeguimientoVista.setLayoutManager(grid);
        adapter = new EjerRegisterRecyclerAdapter(RecoleccionSeguimientoEjercicios());
        AdapterRecomendacionEjercicioSeguimientoVista.setAdapter(adapter);

        Button IrCalendarioEjercicio = findViewById(R.id.IrCalendarioEjer);
        IrCalendarioEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recomendaciones_ejercicios.this, Ejercicios.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<recomendacion_ejercicio_Map> RecoleccionSeguimientoEjercicios(){
        ArrayList<recomendacion_ejercicio_Map> arrayList = new ArrayList<>();
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.ConsultaRecomendacionSeguimientoEjercicio(database);
        while(cursor.moveToNext()){
            String nombre_RE = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_ejercicios.NOMBRE_RE));
            String tipo_grado_RE = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_ejercicios.TIPO_GRADO_RE));
            String calorias_RE = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_ejercicios.CALORIAS_RE));
            String MET_RE = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_ejercicios.MET_RE));
            String tiempo_RE = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_ejercicios.TIEMPO_RE));
            recomendacion_ejercicio_Map registros = new recomendacion_ejercicio_Map(nombre_RE, tipo_grado_RE, calorias_RE, MET_RE, tiempo_RE);
            arrayList.add(registros);
        }
        cursor.close();
        dataBaseOpenHelper.close();
        return arrayList;
    }
}