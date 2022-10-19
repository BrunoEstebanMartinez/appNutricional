package com.example.prueba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class procesoPerfilPuente extends Activity {

    TextView GeneroPuente;
    EditText EstaturaPuente, PesoPuente, EdadPuente;
    ProcesoGenero itemDetailGenero;
    String PuenteGenero, PuentePeso, PuenteEdad, PuenteEstatura;
    Button ProcesoParametrosEnvio;
    AlertDialog alertDialogInstructions;
    AlertDialog.Builder builderInstructions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        builderInstructions = new AlertDialog.Builder(this);
        setContentView(R.layout.activity_proceso_perfil_puente);
        GeneroPuente = findViewById(R.id.ProcesamientoGenero);
        EstaturaPuente = findViewById(R.id.ProcesamientoEstatura);
        PesoPuente = findViewById(R.id.ProcesamientoPeso);
        EdadPuente = findViewById(R.id.ProcesamientoEdad);
        ProcesoParametrosEnvio = findViewById(R.id.ProcesoPuenteDatoa);
        //
        itemDetailGenero = (ProcesoGenero) getIntent().getExtras().getSerializable("itemDetailGenero");
        GeneroPuente.setText(itemDetailGenero.getGenero());
        //



    ProcesoParametrosEnvio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PuenteGenero = GeneroPuente.getText().toString();
            PuentePeso = PesoPuente.getText().toString();
            PuenteEdad = EdadPuente.getText().toString();
            PuenteEstatura = EstaturaPuente.getText().toString();

            if(PuentePeso.equals("") || PuenteEdad.equals("") || PuenteEstatura.equals("")){

                builderInstructions.setTitle("¡Ups!");
                builderInstructions.setMessage("Uno o más campos están vacios");
                builderInstructions.setPositiveButton("Ok", null);
                alertDialogInstructions = builderInstructions.create();
                alertDialogInstructions.show();

            }else {
                Bundle informacionNutricionalUsuario = new Bundle();
                informacionNutricionalUsuario.putString("PuenteGeneroUsuario", PuenteGenero);
                informacionNutricionalUsuario.putString("PuentePesoUsuario", PuentePeso);
                informacionNutricionalUsuario.putString("PuenteEdadUsuario", PuenteEdad);
                informacionNutricionalUsuario.putString("PuenteEstaturaUsuario", PuenteEstatura);
                Intent envio = new Intent(procesoPerfilPuente.this, registroActivity1.class);
                envio.putExtras(informacionNutricionalUsuario);
                startActivity(envio);
            }


        }
    });

    }
}