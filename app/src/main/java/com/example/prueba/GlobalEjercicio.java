package com.example.prueba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.Estructure_usuario;
import DataBaseEstructure.recomendacion_ejercicio_Map;
import DataBaseEstructure.recomendacion_plan_Map;


public class GlobalEjercicio extends Activity {

    TextView NombreView_E, Tipo_gradoView_E, CaloriasView_E, METView_E;
    EditText TiempoView_E;

    TextView ProcesoPeso;
    private recomendacion_ejercicio_Map itemDetail;
    DataBaseOpenHelper dataBaseOpenHelper;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;

    Integer operacion1, operacion2;
    Double operacion3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_ejercicio);
        dataBaseOpenHelper = new DataBaseOpenHelper(this);
        builder = new AlertDialog.Builder(this);
       TiempoView_E = findViewById(R.id.TiempoView);
       ProcesoPeso = findViewById(R.id.PuentePesoEs);
        ConsultaDatosUsuario();
       initViews();
        initValues();



        Button Buttontransicionejercicioguardar = findViewById(R.id.buttontransicionejercicioguardar);
        Button Buttontransicionejerciciocalcular = findViewById(R.id.buttontransicionejerciciocalcular);

        final TextView NombreView_EP = findViewById(R.id.NombreViewEjercicio);
        final TextView Tipo_gradoView_EP =  findViewById(R.id.TipoEjercicioView);
        final TextView CaloriasView_EP =  findViewById(R.id.CaloriasEjercicioView);
        final TextView METView_EP = findViewById(R.id.METEjercicioView);
        //
        final EditText TiempoView_EP = findViewById(R.id.TiempoView);

        //

        Buttontransicionejercicioguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarEjercicioSeguimiento(NombreView_EP.getText().toString(), Tipo_gradoView_EP.getText().toString(), CaloriasView_EP.getText().toString(),
                        METView_EP.getText().toString(), TiempoView_EP.getText().toString());
                AlertDialog();
            }
        });

        Buttontransicionejerciciocalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double METEstandarPorOne = 0.0175;
                String CaloriasEjericio = CaloriasView_E.getText().toString().trim();
                String METEstandar = METView_E.getText().toString().trim();
                String  TiempoProceso = TiempoView_E.getText().toString().trim();
                String ProcesoPes = ProcesoPeso.getText().toString().trim();

                int operacionAlmacenarPeso = Integer.parseInt(ProcesoPes);
                // MET * PESO * MINUTOS

                //
                double operacionAlmacenarMETestandar = Double.parseDouble(METEstandar);
                //
                int operacionAlmacenarTiempoProceso = Integer.parseInt(TiempoProceso);
               // int operacionAlmacenarPesoPuente = Integer.parseInt(VarPeso);

                // Variables para refrescar valores

               final double operacionCalculoCaloriasPorMinuto = operacionAlmacenarMETestandar * METEstandarPorOne * operacionAlmacenarPeso;
               final double  operacionCalculoResult = operacionCalculoCaloriasPorMinuto;
               final double ultimoCalPorMinutoEsfuerzo = operacionCalculoResult * operacionAlmacenarTiempoProceso;
               final long RedondearCalPeerMinuteEsfuerzo = Math.round(ultimoCalPorMinutoEsfuerzo);

               CaloriasView_E.setText(String.valueOf(RedondearCalPeerMinuteEsfuerzo));

            }
        });

        TiempoView_E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaloriasView_E.setText("");
            }
        });

    }
    public void GuardarEjercicioSeguimiento(String nombre_RE, String tipo_grado_RE, String calorias_RE,
                                            String MET_RE, String tiempo_RE) {
        SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
        dataBaseOpenHelper.GuardarEjercicioSeguimiento(nombre_RE, tipo_grado_RE, calorias_RE,
                 MET_RE, tiempo_RE, database);
        dataBaseOpenHelper.close();


    }
    public void ConsultaDatosUsuario(){
        SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
        Cursor cursor = dataBaseOpenHelper.ConsultaPeso(database);
        while(cursor.moveToNext()) {
            ProcesoPeso.setText(cursor.getString(cursor.getColumnIndex(Estructure_usuario.PESO_U)));
        }
        cursor.close();
    }

    public void AlertDialog() {
        builder.setTitle("¡En horabuena!");
        builder.setMessage("Dirigete al apartado 'Tus ejercicios' para consultar tu seguimiento");
        builder.setCancelable(false);
        builder.setPositiveButton("Ir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(GlobalEjercicio.this, Ejercicios.class);
                startActivity(intent);
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void initViews() {
        NombreView_E = findViewById(R.id.NombreViewEjercicio);
        Tipo_gradoView_E = findViewById(R.id.TipoEjercicioView);
        CaloriasView_E = findViewById(R.id.CaloriasEjercicioView);
        METView_E = findViewById(R.id.METEjercicioView);
        TiempoView_E = findViewById(R.id.TiempoView);

    }

    private void initValues() {
        itemDetail = (recomendacion_ejercicio_Map) getIntent().getExtras().getSerializable("itemDetailEjercicio");
        NombreView_E.setText(itemDetail.getNombre_RE());
        Tipo_gradoView_E.setText(itemDetail.getTipo_grado_RE());
        CaloriasView_E.setText(itemDetail.getCalorias_RE());
        METView_E.setText(itemDetail.getMET_RE());
        TiempoView_E.setText(itemDetail.getTiempo_RE());

    }
}