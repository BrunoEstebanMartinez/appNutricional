package com.example.prueba;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.Estructure_recomendacion_plan;
import DataBaseEstructure.recomendacion_plan_Map;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class recomendaciones_plan extends Activity {

    private TextView mTextView;
    List<recomendacion_plan_Map> eventsList = new ArrayList<>();
    DataBaseOpenHelper dataBaseOpenHelper;
    RecyclerView AdapterLayoutRecomendacionSeguimientoVista;
    AlimentosRecyclerAdapter adapter;
    GridLayoutManager grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones_plan);
        dataBaseOpenHelper = new DataBaseOpenHelper(this);
        Button IrACalendario = findViewById(R.id.IrCalendario);

        AdapterLayoutRecomendacionSeguimientoVista = (RecyclerView) findViewById(R.id.AlimentosRegistrados);
        grid = new GridLayoutManager(this, 1);
        AdapterLayoutRecomendacionSeguimientoVista.setLayoutManager(grid);
        adapter = new AlimentosRecyclerAdapter(RecoleccionSeguimientoPlanAlimento());
        AdapterLayoutRecomendacionSeguimientoVista.setAdapter(adapter);

        IrACalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recomendaciones_plan.this, Plan.class);
                startActivity(intent);
            }
        });
    }


    private ArrayList<recomendacion_plan_Map> RecoleccionSeguimientoPlanAlimento(){
        ArrayList<recomendacion_plan_Map> arrayList = new ArrayList<>();
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.ConsultaRecomendacionSeguimientoAlimento(database);
        while (cursor.moveToNext()){
            String NombreVistaPlan = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.NOMBRE_RP));
            String TipoVistaPlan = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.TIPO_RP));
            String NumeroUnidadMedidaVistaPlan = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.NUMERO_UNIDAD_MEDIDA_RP));
            String UnidadDeMedidaVistaPlan = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.UNIDAD_DE_MEDIDA_RP));
            String CaloriasVistaPlan = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.CALORIAS_RP));
            String ProteinasVistaPlan = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.PROTEINAS_RP));
            String CarbohidratosVistaPlan = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.CARBOHIDRATOS_RP));
            String GrasasVistaPlan = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.GRASAS_RP));
            String CantidadVistaPlan = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.CANTIDAD_RP));
            String ConceptoVistaPlan = cursor.getString(cursor.getColumnIndex(Estructure_recomendacion_plan.CONCEPTO_RP));
            recomendacion_plan_Map registros = new recomendacion_plan_Map(NombreVistaPlan, TipoVistaPlan,
                    NumeroUnidadMedidaVistaPlan, UnidadDeMedidaVistaPlan, CaloriasVistaPlan, ProteinasVistaPlan,
                    CarbohidratosVistaPlan, GrasasVistaPlan, CantidadVistaPlan, ConceptoVistaPlan);
            arrayList.add(registros);
        }
        cursor.close();
        dataBaseOpenHelper.close();

        return arrayList;
    }

    public void onBackPressed(){
        Intent intent = new Intent(recomendaciones_plan.this, Plan.class);
        startActivity(intent);
    }
}