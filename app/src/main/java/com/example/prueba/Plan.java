package com.example.prueba;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.Estructure_recomendacion_plan;
import DataBaseEstructure.Estructure_requerimiento_gramos;
import DataBaseEstructure.Estructure_requerimientos;
import DataBaseEstructure.Estructure_usuario;


public class Plan extends Activity {

    protected LinearLayout BuscarAlimento, SeguimientoAlimento;
    protected ImageView ButtonBack;
    TextView PesoVistaText, VistaEstaturaText, VistaEdadText, VistaKilocaloriasText, VistaCaloriasGrasasText,
            VistaCaloriasPorGramoGrasas, VistaCaloriasProteinasText, VistaCaloriasPorGramoProteinasText,
            VistaCaloriasCarbohidratosText, VistaCaloriasPorGramoCarbohidratosText, SumaCalorias;
    DataBaseOpenHelper dataBaseOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        //

        //
        dataBaseOpenHelper = new DataBaseOpenHelper(this);


        PesoVistaText = findViewById(R.id.PesoVista);
        VistaEstaturaText = findViewById(R.id.VistaEstatura);
        VistaEdadText = findViewById(R.id.VistaEdad);
        VistaKilocaloriasText = findViewById(R.id.VistaKilocalorias);
        VistaCaloriasGrasasText = findViewById(R.id.VistaCaloriasGrasas);
        VistaCaloriasPorGramoGrasas  = findViewById(R.id.VistaCaloriasPorGramoGrasas);
        VistaCaloriasProteinasText  = findViewById(R.id.VistaCaloriasProteinas);
        VistaCaloriasPorGramoProteinasText = findViewById(R.id.VistaCaloriasPorGramoProteinas);
        VistaCaloriasCarbohidratosText  = findViewById(R.id.VistaCaloriasCarbohidratos);
        VistaCaloriasPorGramoCarbohidratosText = findViewById(R.id.VistaCaloriasPorGramoCarbohidratos);


        SumaCalorias = findViewById(R.id.SumaCal);

       BuscarAlimento = findViewById(R.id.Buscaralimento);
       SeguimientoAlimento = findViewById(R.id.TusAlimentos);

        TransicionAgregadoPlan();

        ConsultaDatosUsuario();
        ConsultaDatosRequerimientos();
        ConsultaDatosRequGramos();
        SumaProceso();

    }

    private void TransicionAgregadoPlan(){
   BuscarAlimento.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent intent = new Intent(Plan.this, transicionplan.class);
           startActivity(intent);
           SeguimientoAlimento.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent1 = new Intent(Plan.this, recomendaciones_plan.class);
                   startActivity(intent1);
               }
           });

       }
   });

    }

    public void ConsultaDatosUsuario(){
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.ConsultaPeso(database);
        while(cursor.moveToNext()) {
            PesoVistaText.setText(cursor.getString(cursor.getColumnIndex(Estructure_usuario.PESO_U)));
            VistaEstaturaText.setText(cursor.getString(cursor.getColumnIndex(Estructure_usuario.ESTATURA_U)));
            VistaEdadText.setText(cursor.getString(cursor.getColumnIndex(Estructure_usuario.EDAD_U)));
        }
        cursor.close();
    }

    public void ConsultaDatosRequerimientos(){
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.ConsultaPerfilRequerimientos(database);
        while(cursor.moveToNext()){
            VistaKilocaloriasText.setText(cursor.getString(cursor.getColumnIndex(Estructure_requerimientos.KILOCALORIAS_R)));
            VistaCaloriasGrasasText.setText(cursor.getString(cursor.getColumnIndex(Estructure_requerimientos.GRASAS_R)));
            VistaCaloriasCarbohidratosText.setText(cursor.getString(cursor.getColumnIndex(Estructure_requerimientos.CARBOHIDRATOS_R)));
            VistaCaloriasProteinasText.setText(cursor.getString(cursor.getColumnIndex(Estructure_requerimientos.PROTEINAS_R)));

        }
        cursor.close();
    }

    public void ConsultaDatosRequGramos(){
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.ConsultaPerfilRequerimientosGramos(database);
        while(cursor.moveToNext()){
            VistaCaloriasPorGramoCarbohidratosText.setText(cursor.getString(cursor.getColumnIndex(Estructure_requerimiento_gramos.CARBOHIDRATOS_G)));
            VistaCaloriasPorGramoGrasas.setText(cursor.getString(cursor.getColumnIndex(Estructure_requerimiento_gramos.GRASAS_G)));
            VistaCaloriasPorGramoProteinasText.setText(cursor.getString(cursor.getColumnIndex(Estructure_requerimiento_gramos.PROTEINAS_G)));
        }
        cursor.close();
    }


    public void SumaProceso(){
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.SumaKilocalorias(database);
        while(cursor.moveToNext()){
            SumaCalorias.setText(cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.CALORIAS_RP)));
        }
        cursor.close();
        dataBaseOpenHelper.close();
    }

    public void onBackPressed(){
        Intent intent = new Intent(Plan.this, menu.class);
        startActivity(intent);
    }
}