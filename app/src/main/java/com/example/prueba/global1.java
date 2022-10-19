package com.example.prueba;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.Estructure_Plan;
import DataBaseEstructure.Estructure_recomendacion_plan;
import DataBaseEstructure.Estructure_usuario;



public class global1 extends Activity {
    DataBaseOpenHelper dataBaseOpenHelper;
    TextView PesoVistaText, VistaEstaturaText, VistaEdadText, VistaKilocaloriasText, VistaCaloriasGrasasText,
            VistaCaloriasPorGramoGrasas, VistaCaloriasProteinasText, VistaCaloriasPorGramoProteinasText,
            VistaCaloriasCarbohidratosText, VistaCaloriasPorGramoCarbohidratosText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global1);

        PesoVistaText = findViewById(R.id.PesoVista);
        VistaEstaturaText = findViewById(R.id.VistaEstatura);
        VistaEdadText = findViewById(R.id.VistaEdad);
        VistaKilocaloriasText = findViewById(R.id.VistaKilocalorias);
        VistaCaloriasGrasasText = findViewById(R.id.VistaCaloriasGrasas);
        VistaCaloriasPorGramoGrasas = findViewById(R.id.VistaCaloriasPorGramoGrasas);
        VistaCaloriasProteinasText = findViewById(R.id.VistaCaloriasProteinas);
        VistaCaloriasPorGramoProteinasText = findViewById(R.id.VistaCaloriasPorGramoProteinas);
        VistaCaloriasCarbohidratosText = findViewById(R.id.VistaCaloriasCarbohidratos);
        VistaCaloriasPorGramoCarbohidratosText = findViewById(R.id.VistaCaloriasPorGramoCarbohidratos);




    }

    public void ConsultaDatosUsuario() {
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.ConsultaPeso(database);
        while (cursor.moveToNext()) {
            PesoVistaText.setText(cursor.getString(cursor.getColumnIndex(Estructure_usuario.PESO_U)));
            VistaEstaturaText.setText(cursor.getString(cursor.getColumnIndex(Estructure_usuario.ESTATURA_U)));
            VistaEdadText.setText(cursor.getString(cursor.getColumnIndex(Estructure_usuario.EDAD_U)));
        }
        cursor.close();
    }
}
