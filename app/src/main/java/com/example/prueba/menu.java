package com.example.prueba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.Estructure_requerimientos;


public class menu extends Activity {
   DataBaseOpenHelper dataBaseOpenHelper;
    LinearLayout Seguimiento, Plan, Ejercicio, imageViewicon;
    AlertDialog alertDialogInstructions;
    AlertDialog.Builder builderInstructions;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        builderInstructions = new AlertDialog.Builder(this);
       //
      dataBaseOpenHelper = new DataBaseOpenHelper(this);

      //
        Seguimiento = (LinearLayout) findViewById(R.id.seguimiento);
        Plan = (LinearLayout) findViewById(R.id.plan);
        Ejercicio =  (LinearLayout) findViewById(R.id.ejercicio);
        imageViewicon = findViewById(R.id.Inicio);
     MenuLinear();

    }
    public void MenuLinear(){

        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.ConsultaPerfilRequerimientos(database);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            Seguimiento.setEnabled(false);
            Plan.setEnabled(false);
            Ejercicio.setEnabled(false);
            //
        }
        cursor.close();
        // <--------------------------
        Seguimiento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, Principal.class);
                startActivity(intent);
            }
        });

        // <--------------------------
        Plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, Plan.class);

                startActivity(intent);
            }
        });
        // <--------------------------
        Ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, Ejercicios.class);
                startActivity(intent);
            }
        });
        // <--------------------------
        imageViewicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }




    public void onBackPressed() {
        // <--------------------------

                builderInstructions.setTitle("¡Ups!");
                builderInstructions.setMessage("Presiona el apartado de 'Personal' para iniciar");
                builderInstructions.setCancelable(false);
                builderInstructions.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(menu.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                builderInstructions.setNegativeButton("Tengo un perfil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       builderInstructions.setCancelable(true);
                    }
                });
                alertDialogInstructions = builderInstructions.create();
                alertDialogInstructions.show();

    }

}