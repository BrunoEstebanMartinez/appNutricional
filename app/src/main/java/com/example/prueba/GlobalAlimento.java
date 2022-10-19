package com.example.prueba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import DataBaseEstructure.DataBaseOpenHelper;
import DataBaseEstructure.recomendacion_plan_Map;


public class GlobalAlimento extends Activity {
    //
    TextView NombreView_F, ViewTipo_F, ViewPorcion_F, CaloriasView_F, Carbohidratos_F,
            ProteinasView_F, GrasasView_F, UnidadMedidaView_F, ConceptoView_F;

    EditText CantidadView_F;
    //
    private recomendacion_plan_Map itemDetail;
    //
    DataBaseOpenHelper dataBaseOpenHelper;
    //
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    // Operadores
    Double operacion1, operacion2, operacion3, operacion4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_alimento);
        dataBaseOpenHelper = new DataBaseOpenHelper(this);
        builder = new AlertDialog.Builder(this);
        initViews();
        initValues();

        //
        Button Buttontransicioncomidaguardar = findViewById(R.id.buttontransicionguardar);
        Button Buttontransicioncomidacalcular = findViewById(R.id.buttontransicioncalcular);
        EditText trancisioncomidarefrescar = findViewById(R.id.CantidadView);
        //
        final TextView NombreAlimento = findViewById(R.id.NombreView);
        final TextView TipoAlimento =  findViewById(R.id.ViewTipo);
        final TextView PorcionAlimento =  findViewById(R.id.ViewPorcion);
        final TextView CaloriasAlimento = findViewById(R.id.CaloriasView);
        final TextView CarbohidratosAlimento = findViewById(R.id.CarbohidratosView);
        final TextView ProteinasAlimento =  findViewById(R.id.ProteinasView);
        final TextView GrasasAlimento = findViewById(R.id.GrasasView);
        final TextView UnidadMedidaAlimento = findViewById(R.id.UnidadMedidaView);
        final TextView ConceptoAlimento = findViewById(R.id.ConceptoView);

        //
        final EditText CantidadAlimento = findViewById(R.id.CantidadView);
        //

        //
        Buttontransicioncomidaguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcesoAlimento(NombreAlimento.getText().toString(), TipoAlimento.getText().toString(), PorcionAlimento.getText().toString(),
                        UnidadMedidaAlimento.getText().toString(), CaloriasAlimento.getText().toString(), ProteinasAlimento.getText().toString(),
                        CarbohidratosAlimento.getText().toString(), GrasasAlimento.getText().toString(), CantidadAlimento.getText().toString(),
                        ConceptoAlimento.getText().toString());
                AlertDialog();
            }
        });
            Buttontransicioncomidacalcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String CantidadProceso = CantidadView_F.getText().toString().trim();
                    String Porcion = ViewPorcion_F.getText().toString().trim();
                    String Calorias = CaloriasView_F.getText().toString().trim();
                    String Carbohidratos = Carbohidratos_F.getText().toString().trim();
                    String Proteinas = ProteinasView_F.getText().toString().trim();
                    String Grasas = GrasasView_F.getText().toString().trim();

                    //Valor calorico

                   double operacionAlmacenarTextoCalorias = Double.parseDouble(Calorias);
                   double operacionAlmacenarTextoCarbohidratos = Double.parseDouble(Carbohidratos);
                   double operacionAlmacenarTextoProteinas = Double.parseDouble(Proteinas);
                   double operacionAlmacenarTextoGrasas = Double.parseDouble(Grasas);
                   //Variables para refrescar a valores originales
                   operacion1 = operacionAlmacenarTextoCalorias;
                   operacion2 = operacionAlmacenarTextoCarbohidratos;
                   operacion3 = operacionAlmacenarTextoProteinas;
                   operacion4 =  operacionAlmacenarTextoGrasas;
                  // int operacion2 = Integer.parseInt(Carbohidratos);
                   //int operacion3 = Integer.parseInt(Proteinas);
                   //int operacion4 = Integer.parseInt(Grasas);
                   int operacionAlmacenarTextoCantidad = Integer.parseInt(CantidadProceso);

                    //Calculos
                    final double operacionAlmacenarCalculoCalCant = operacionAlmacenarTextoCalorias * operacionAlmacenarTextoCantidad;
                    final double operacionAlmacenarCalculoCarCant = operacionAlmacenarTextoCarbohidratos * operacionAlmacenarTextoCantidad;
                    final double operacionAlmacenarCalculoProtCant = operacionAlmacenarTextoProteinas * operacionAlmacenarTextoCantidad;
                    final double operacionAlmacenarCalculoGrasCant = operacionAlmacenarTextoGrasas * operacionAlmacenarTextoCantidad;

                    //Redondeos
                    final long operacionAlmacenarCalculoCalRed = Math.round(operacionAlmacenarCalculoCalCant);
                    final long operacionAlmacenarCalculoCarRed = Math.round(operacionAlmacenarCalculoCarCant);
                    final long operacionAlmacenarCalculoProtRed = Math.round(operacionAlmacenarCalculoProtCant);
                    final long operacionAlmacenarCalucloGrasRed = Math.round(operacionAlmacenarCalculoGrasCant);
                    //Vistas
                    CaloriasView_F.setText(String.valueOf(operacionAlmacenarCalculoCalRed));
                    Carbohidratos_F.setText(String.valueOf(operacionAlmacenarCalculoCarRed));
                    ProteinasView_F.setText(String.valueOf(operacionAlmacenarCalculoProtRed));
                    GrasasView_F.setText(String.valueOf(operacionAlmacenarCalucloGrasRed));

                }
            });

        trancisioncomidarefrescar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CaloriasView_F.setText(String.valueOf(operacion1));
                    Carbohidratos_F.setText(String.valueOf(operacion2));
                    ProteinasView_F.setText(String.valueOf(operacion3));
                    GrasasView_F.setText(String.valueOf(operacion4));
                }
            });
    }

    public void ProcesoAlimento(String nombre_RP, String tipo_RP, String numero_unidad_medida_RP,
                                String unidad_de_medida_RP, String calorias_RP, String proteinas_RP,
                                String carbohidrados_RP, String grasas_RP, String cantidad_RP, String concepto_RP) {
        SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
        dataBaseOpenHelper.GuardarAlimento(nombre_RP, tipo_RP, numero_unidad_medida_RP,
                unidad_de_medida_RP, calorias_RP, proteinas_RP,
                carbohidrados_RP, grasas_RP, cantidad_RP, concepto_RP, database);
        dataBaseOpenHelper.close();


    }

    public void AlertDialog() {
        builder.setTitle("¡En horabuena!");
        builder.setMessage("Dirigete al apartado 'Tus alimentos' para consultar tu seguimiento");
        builder.setCancelable(false);
        builder.setPositiveButton("Ir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(GlobalAlimento.this, recomendaciones_plan.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Seguir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               builder.setCancelable(true);
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void initViews() {
        NombreView_F = findViewById(R.id.NombreView);
        ViewTipo_F = findViewById(R.id.ViewTipo);
        ViewPorcion_F = findViewById(R.id.ViewPorcion);
        CaloriasView_F = findViewById(R.id.CaloriasView);
        Carbohidratos_F = findViewById(R.id.CarbohidratosView);
        ProteinasView_F = findViewById(R.id.ProteinasView);
        GrasasView_F = findViewById(R.id.GrasasView);
        UnidadMedidaView_F = findViewById(R.id.UnidadMedidaView);
        CantidadView_F = findViewById(R.id.CantidadView);
        ConceptoView_F = findViewById(R.id.ConceptoView);
        //

        //

    }

    private void initValues() {
        itemDetail = (recomendacion_plan_Map) getIntent().getExtras().getSerializable("itemDetail");
        NombreView_F.setText(itemDetail.getNombre_RP());
        ViewTipo_F.setText(itemDetail.getTipo_RP());
        ViewPorcion_F.setText(itemDetail.getNumero_unidad_medida_RP());
        CaloriasView_F.setText(itemDetail.getCalorias_RP());
        Carbohidratos_F.setText(itemDetail.getCarbohidrados_RP());
        ProteinasView_F.setText(itemDetail.getProteinas_RP());
        GrasasView_F.setText(itemDetail.getGrasas_RP());
        UnidadMedidaView_F.setText(itemDetail.getUnidad_de_medida_RP());
        CantidadView_F.setText(itemDetail.getCantidad_RP());
        ConceptoView_F.setText(itemDetail.getConcepto_RP());

    }




}
