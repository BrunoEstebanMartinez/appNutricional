package com.example.prueba;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import DataBaseEstructure.DataBaseOpenHelper;


public class registroActivity1 extends Activity{
    DataBaseOpenHelper dataBaseOpenHelper;
    //
    AlertDialog alertDialogInstructions;
    AlertDialog.Builder builderInstructions;
    //
    AlertDialog alertInstructionsInterface;
    AlertDialog.Builder builderInstructionsInterface;
    // Componentes de diseño de layout global1
    TextView PesoVista, EstaturaVista, EdadVista, KilocaloriasVista, GrasasCaloriasVista,
            GrasasCaloriasGramosVista, ProteinasCaloriasVista, ProteinasCaloriasGramosVista,
            CarbohidratosCaloriasVista, CarbohidratosCaloriasGramosVista;
    //
    String Validacion;
    // Vista de calculos
    TextView GeneroGlobal, TextProceso, TextPesoPrincipal, TextEstaturaPrincipal,
            TextEdadPrincipal, TextCaloriasCarbohidratosPrincipal,
            TextCaloriasProteinasPrincipal, TextCaloriasGrasasPrincipal,
            TextCaloriasCarbohidratosGramosPrincipal, TextCaloriasProteinasGramosPrincipal,
            TextCaloriasGrasasGramosPrincipal, TextBasalPrincipal, TextAguaPrincipal;
   // Puente para visualizacion en interfaz principal
    String TextProcesoKilos, TextPesoPrincipalPuente, TextEstaturaPrincipalPuente,
           TextEdadPrincipalPuente, TextCaloriasCarbohidratosPrincipalPuente,
           TextCaloriasProteinasPrincipalPuente, TextCaloriasGrasasPrincipalPuente,
           TextCaloriasCarbohidratosGramosPrincipalPuente, TextCaloriasProteinasGramosPrincipalPuente,
           TextCaloriasGrasasGramosPrincipalPuente, TextBasalPrincipalPuente, TextAguaPrincipalPuente;
    //
    Button Calculo, Guardar;
    // Calculo de caloria basal con esfuerzo físico
    Double operacion1, operacion2, operacion3, operacion4, operacion5, operacion6;
    // Mostras kilocalorias (redondeo)
    Long operacion7;
    // Redondeo basal
    Long operacion23;
    //
    // Paso de parametros de puente
    String Peso, Estatura, Edad, Genero;
    // Calculo requerimientos en calorias de dieta equilibrada
    Double operacion8, operacion9, operacion10;
    // Redondeo requerimientos en calorias de dieta equilibrada
    Long operacion11, operacion12, operacion13;
    // Calculo requerimientos de calorias por gramo por día
    Long operacion14, operacion15, operacion16;
    //Redondeo requerimientos de calorias por gramos por dia
    Integer operacion17, operacion18, operacion19;
    // Calculo agua requerimiento
    Integer operacion20;
    // Redondeo requerimiento agua
    Integer operacion22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro1);
        dataBaseOpenHelper = new DataBaseOpenHelper(this);
        //
        builderInstructions = new AlertDialog.Builder(this);
        builderInstructionsInterface = new AlertDialog.Builder(this);
        // Componentes de vista de calculos
        GeneroGlobal = findViewById(R.id.GeneroGenero);
        TextPesoPrincipal = findViewById(R.id.textPeso);
        TextEstaturaPrincipal = findViewById(R.id.textaltura);
        TextEdadPrincipal = findViewById(R.id.textEdad);
        TextProceso = findViewById(R.id.textProceso); // -> Kilocalorias
        TextCaloriasCarbohidratosPrincipal = findViewById(R.id.textProcesoCaloriasdeCarbohidratos);
        TextCaloriasProteinasPrincipal = findViewById(R.id.textProcesoCaloriasdeProteinas);
        TextCaloriasGrasasPrincipal = findViewById(R.id.textProcesoCaloriasdeGrasas);
        TextCaloriasCarbohidratosGramosPrincipal = findViewById(R.id.textProcesoCaloriasPorGramoCarbs);
        TextCaloriasProteinasGramosPrincipal = findViewById(R.id.textProcesoCaloriasPorGramoProt);
        TextCaloriasGrasasGramosPrincipal = findViewById(R.id.textProcesoCaloriasPorGramoGras);
        TextBasalPrincipal = findViewById(R.id.textProcesoBasal);
        TextAguaPrincipal = findViewById(R.id.textProcesoAgua);
       //Botones de proceso
        Calculo = findViewById(R.id.ProcesoCalculo);
        Guardar = findViewById(R.id.ProcesoPuenteDatosGuardar);
     // Puente de perfil de usuario
        Bundle Recibidos = this.getIntent().getExtras();
        Peso = Recibidos.getString("PuentePesoUsuario");
        Estatura = Recibidos.getString("PuenteEstaturaUsuario");
        Edad = Recibidos.getString("PuenteEdadUsuario");
        Genero = Recibidos.getString("PuenteGeneroUsuario");

        GeneroGlobal.setText(Genero);
        TextPesoPrincipal.setText(Peso);
        TextEstaturaPrincipal.setText(Estatura);
        TextEdadPrincipal.setText(Edad);
        //
     Peso = TextPesoPrincipal.getText().toString().trim();
     Estatura = TextEstaturaPrincipal.getText().toString().trim();
     Edad = TextEdadPrincipal.getText().toString().trim();
     Validacion = GeneroGlobal.getText().toString();
     //
     Calculo.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(Validacion.contentEquals("Hombre")){
                 operacion1 = 13.75 * ParseInt(Peso);
                 operacion2 = 5.0 * ParseInt2(Estatura);
                 operacion3 = 6.78 * ParseInt3(Edad);
                 operacion4 = operacion1 + operacion2 - operacion3;
                 // Requerimiento basal (Kilocalorias por vivir)
                 operacion5 = 66.5 + operacion4;
                 operacion23 = Math.round(operacion5);
                 // Requerimiento basal por esfuerzo fisico (kilocalorias generales)
                 operacion6 = operacion5 * 1.78;
                 operacion7 = Math.round(operacion6);
                 // -> Distribucion de macronutrientes
                 //Calorias de carbohidratos
                 operacion8 = operacion7 * .50;
                 operacion11 = Math.round(operacion8);
                 //Calorias de proteinas
                 operacion9 = operacion7 * .25;
                 operacion12 = Math.round(operacion9);
                 // Calorias de Grasas
                 operacion10 = operacion7 * .25;
                 operacion13 = Math.round(operacion10);
                 // ->
                 // Calorias por gramo de carbohidratos
                 operacion14 = operacion11 / 4;
                 operacion17 = Math.round(operacion14);
                 // Calorias por gramo de proteinas
                 operacion15 = operacion12 / 4;
                 operacion18 = Math.round(operacion15);
                 // Calorias por gramo de grasa
                 operacion16 = operacion13 / 9;
                 operacion19 = Math.round(operacion16);
                 //->
                 // Requerimiento agua
                 operacion20 = ParseInt(Peso) * 35;
                 operacion22 = Math.round(operacion20);
                 //Visualizacion de datos calculados
                 TextProceso.setText(operacion7.toString());
                 TextCaloriasCarbohidratosPrincipal.setText(operacion11.toString());
                 TextCaloriasProteinasPrincipal.setText(operacion12.toString());
                 TextCaloriasGrasasPrincipal.setText(operacion13.toString());
                 TextCaloriasCarbohidratosGramosPrincipal.setText(operacion17.toString());
                 TextCaloriasProteinasGramosPrincipal.setText(operacion18.toString());
                 TextCaloriasGrasasGramosPrincipal.setText(operacion19.toString());
                 TextBasalPrincipal.setText(operacion23.toString());
                 TextAguaPrincipal.setText(operacion22.toString());
             }else if(Validacion.contentEquals("Mujer")){
                 operacion1 = 9.56 * ParseInt(Peso);
                 operacion2 = 1.85 * ParseInt2(Estatura);
                 operacion3 = 4.68 * ParseInt2(Edad);
                 operacion4 = operacion1 + operacion2 - operacion3;
                 // Requerimiento basal
                 operacion5 = 66.5 + operacion4;
                 operacion23 = Math.round(operacion5);
                 // Requerimiento basal por esfuerzo fisico
                 operacion6 = operacion5 * 1.64;
                 operacion7 = Math.round(operacion6);
                 //Calorias de carbohidratos
                 operacion8 = operacion7 * .50;
                 operacion11 = Math.round(operacion8);
                 //Calorias de proteinas
                 operacion9 = operacion7 * .25;
                 operacion12 = Math.round(operacion9);
                 // Calorias de Grasas
                 operacion10 = operacion7 * .25;
                 operacion13 = Math.round(operacion10);
                 // Calorias por gramo de carbohidratos
                 operacion14 = operacion11 / 4;
                 operacion17 = Math.round(operacion14);
                 // Calorias por gramo de proteinas
                 operacion15 = operacion12 / 4;
                 operacion18 = Math.round(operacion15);
                 // Calorias por gramo de grasa
                 operacion16 = operacion13 / 9;
                 operacion19 = Math.round(operacion16);
                 // Requerimiento agua
                 operacion20 = ParseInt(Peso) * 35;
                 operacion22 = Math.round(operacion20);
                 
                 TextProceso.setText(operacion7.toString());
                 TextCaloriasCarbohidratosPrincipal.setText(operacion11.toString());
                 TextCaloriasProteinasPrincipal.setText(operacion12.toString());
                 TextCaloriasGrasasPrincipal.setText(operacion13.toString());
                 TextCaloriasCarbohidratosGramosPrincipal.setText(operacion17.toString());
                 TextCaloriasProteinasGramosPrincipal.setText(operacion18.toString());
                 TextCaloriasGrasasGramosPrincipal.setText(operacion19.toString());
                 TextBasalPrincipal.setText(operacion23.toString());
                 TextAguaPrincipal.setText(operacion22.toString());
             }

         }
     });



     Guardar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String Proceso = TextProceso.getText().toString();
            String CalCarPr = TextCaloriasCarbohidratosPrincipal.getText().toString();
             String ProCarPr = TextCaloriasProteinasPrincipal.getText().toString();
             String GrasCarPr = TextCaloriasGrasasPrincipal.getText().toString();
             String CarboGramCar = TextCaloriasCarbohidratosGramosPrincipal.getText().toString();
             String ProteGramCar = TextCaloriasProteinasGramosPrincipal.getText().toString();
             String GrasGramCar = TextCaloriasGrasasGramosPrincipal.getText().toString();
             String BaslTexto = TextBasalPrincipal.getText().toString();
            String AguaTexto =  TextAguaPrincipal.getText().toString();

             if(Proceso.equals("") || CalCarPr.equals("") || ProCarPr.equals("") || GrasCarPr.equals("") ||
             CarboGramCar.equals("") || ProteGramCar.equals("") || GrasGramCar.equals("") || BaslTexto.equals("") ||
             AguaTexto.equals("")) {
                 builderInstructions.setTitle("¡Ups!");
                 builderInstructions.setMessage("Uno o más campos están vacios, para rellenar pulse el boton 'Previo'");
                 builderInstructions.setPositiveButton("Ok", null);
                 alertDialogInstructions = builderInstructions.create();
                 alertDialogInstructions.show();
             }else {

                 // Metodos de bd
                 ProcesoUsuarioRegistro(GeneroGlobal.getText().toString(), TextPesoPrincipal.getText().toString(),
                         TextEstaturaPrincipal.getText().toString(), TextEdadPrincipal.getText().toString());
                 ProcesoRegistroRequerimientos(TextProceso.getText().toString(), TextCaloriasProteinasPrincipal.getText().toString(),
                         TextCaloriasCarbohidratosPrincipal.getText().toString(),
                         TextCaloriasGrasasPrincipal.getText().toString(),
                         TextBasalPrincipal.getText().toString(), TextAguaPrincipal.getText().toString());
                 ProcesoRegistroRequerimientosGramos(TextCaloriasProteinasGramosPrincipal.getText().toString(),
                         TextCaloriasCarbohidratosGramosPrincipal.getText().toString(),
                         TextCaloriasGrasasGramosPrincipal.getText().toString());

                 builderInstructionsInterface.setTitle("¡En horabuena!");
                 builderInstructionsInterface.setMessage("Consulta tu requerimiento en el apartado 'Inicio'");
                 builderInstructionsInterface.setCancelable(false);
                 builderInstructionsInterface.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         Intent intent = new Intent(registroActivity1.this, Principal.class);
                         startActivity(intent);
                     }
                 });
                 builderInstructionsInterface.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         Intent intent2 = new Intent(registroActivity1.this, menu.class);
                         startActivity(intent2);
                     }
                 });
                 alertInstructionsInterface = builderInstructionsInterface.create();
                 alertInstructionsInterface.show();
             }
         }
     });
    }


   Integer ParseInt(String Peso) {
        if (Peso != null && Peso.length() > 0) {
            try {
                return Integer.parseInt(Peso);
            } catch(Exception e) {
                return -1;
            }
        }
        else return 0;
    }

    Integer ParseInt2(String Estatura) {
        if (Peso != null && Peso.length() > 0) {
            try {
                return Integer.parseInt(Estatura);
            } catch(Exception e) {
                return -1;
            }
        }
        else return 0;
    }
    Integer ParseInt3(String Edad) {
        if (Peso != null && Peso.length() > 0) {
            try {
                return Integer.parseInt(Edad);
            } catch(Exception e) {
                return -1;
            }
        }
        else return 0;
    }


    public void ProcesoUsuarioRegistro(String genero, String peso, String estatura,
                                       String edad){
         SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
         dataBaseOpenHelper.GuardarUsuario(genero, peso, estatura,
                edad, database);
         dataBaseOpenHelper.close();
    }
    public void ProcesoRegistroRequerimientos(String kilocalorias_R, String proteinas_R,
                                              String carbohidratos_R,
                                              String grasas_R, String basal_R, String agua_R){
        SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
        dataBaseOpenHelper.GuardarPerfilUsuarioRequerimientos(kilocalorias_R, proteinas_R, carbohidratos_R,
                grasas_R, basal_R, agua_R, database);
        dataBaseOpenHelper.close();
    }

    public void ProcesoRegistroRequerimientosGramos(String proteinas_G, String carbohidratos_G,
                                                    String grasas_G){
        SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
        dataBaseOpenHelper.GuardarPerfilUsuarioRequerimientosGramos(proteinas_G, carbohidratos_G, grasas_G, database);
        dataBaseOpenHelper.close();
    }

    }
