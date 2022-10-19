package DataBaseEstructure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    public DataBaseOpenHelper(Context context) {
        super(context, Estructure_DBName_DBVersion.DATABASENAME, null, Estructure_DBName_DBVersion.VERSIONDATABASE);

    }

    @Override
    public void onCreate(SQLiteDatabase DataBasePersonal) {
        DataBasePersonal.execSQL(Estructure_Plan.CREAR_TABLA_PLAN);
        DataBasePersonal.execSQL(Estructure_recomendacion_plan.CREAR_TABLA_RECOMENDACION_PLAN);
        DataBasePersonal.execSQL(Estructure_Ejercicios.CREAR_TABLA_EJERCICIO);
        DataBasePersonal.execSQL(Estructure_recomendacion_ejercicios.CREAR_TABLA_RECOMENDACION_EJERCICIOS);
        DataBasePersonal.execSQL(Estructure_usuario.CREAR_TABLA_USUARIO);
        DataBasePersonal.execSQL(Estructure_requerimientos.CREAR_TABLA_REQUERIMIENTOS);
        DataBasePersonal.execSQL(Estructure_requerimiento_gramos.CREAR_TABLA_REQUERIMIENTO_GRAMOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase DataBasePersonal, int oldVersion, int newVersion) {
        DataBasePersonal.execSQL(Estructure_Plan.ELIMINAR_TABLA_PLAN);
        DataBasePersonal.execSQL(Estructure_recomendacion_plan.ELIMINAR_TABLA_RECOMENDACIONES_PLAN);
        DataBasePersonal.execSQL(Estructure_Ejercicios.ELIMINAR_TABLA_EJERCICIO);
        DataBasePersonal.execSQL(Estructure_recomendacion_ejercicios.ELIMINAR_TABLA_RECOMENDACION_EJERCICIOS);
        DataBasePersonal.execSQL(Estructure_usuario.ELIMINAR_TABLA_USUARIO);
        DataBasePersonal.execSQL(Estructure_requerimientos.ELIMINAR_TABLA_REQUERIMIENTOS);
        DataBasePersonal.execSQL(Estructure_requerimiento_gramos.ELIMINAR_TABLA_REQUERIMIENTO_GRAMOS);
        onCreate(DataBasePersonal);
    }


    // Guardar plan en calendario
    public void GuardarPlan(String tipo_concepto, String nombre_comida, String event, String time,
                            String date, String month, String year, String notificacion, SQLiteDatabase database) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Estructure_Plan.TIPO_CONCEPTO, tipo_concepto);
        contentValues.put(Estructure_Plan.NOMBRE_COMIDA, nombre_comida);
        contentValues.put(Estructure_Plan.EVENT, event);
        contentValues.put(Estructure_Plan.TIME, time);
        contentValues.put(Estructure_Plan.DATE, date);
        contentValues.put(Estructure_Plan.MONTH, month);
        contentValues.put(Estructure_Plan.YEAR, year);
        contentValues.put(Estructure_Plan.NOTIFICACION, notificacion);
        database.insert(Estructure_Plan.TABLA_PLAN, null, contentValues);
    }

    //Guardar alimento usuario
    public void GuardarAlimento(String nombre_RP, String tipo_RP, String numero_unidad_medida_RP,
                                String unidad_de_medida_RP, String calorias_RP, String proteinas_RP,
                                String carbohidrados_RP, String grasas_RP, String cantidad_RP, String concepto_RP, SQLiteDatabase database) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Estructure_recomendacion_plan.NOMBRE_RP, nombre_RP);
        contentValues.put(Estructure_recomendacion_plan.TIPO_RP, tipo_RP);
        contentValues.put(Estructure_recomendacion_plan.NUMERO_UNIDAD_MEDIDA_RP, numero_unidad_medida_RP);
        contentValues.put(Estructure_recomendacion_plan.UNIDAD_DE_MEDIDA_RP, unidad_de_medida_RP);
        contentValues.put(Estructure_recomendacion_plan.CALORIAS_RP, calorias_RP);
        contentValues.put(Estructure_recomendacion_plan.PROTEINAS_RP, proteinas_RP);
        contentValues.put(Estructure_recomendacion_plan.CARBOHIDRATOS_RP, carbohidrados_RP);
        contentValues.put(Estructure_recomendacion_plan.GRASAS_RP, grasas_RP);
        contentValues.put(Estructure_recomendacion_plan.CANTIDAD_RP, cantidad_RP);
        contentValues.put(Estructure_recomendacion_plan.CONCEPTO_RP, concepto_RP);
        database.insert(Estructure_recomendacion_plan.TABLA_RECOMENDACION_PLAN, null, contentValues);

    }

    public Cursor SumaKilocalorias(SQLiteDatabase database){

              return  database.rawQuery(Estructure_recomendacion_plan.CONSULTA_SUMA,null);
    }

    public Cursor ConsultaRecomendacionSeguimientoAlimento(SQLiteDatabase database){
        String [] Vistas = {Estructure_recomendacion_plan.NOMBRE_RP, Estructure_recomendacion_plan.TIPO_RP,
                Estructure_recomendacion_plan.NUMERO_UNIDAD_MEDIDA_RP, Estructure_recomendacion_plan.UNIDAD_DE_MEDIDA_RP,
                Estructure_recomendacion_plan.CALORIAS_RP, Estructure_recomendacion_plan.PROTEINAS_RP, Estructure_recomendacion_plan.CARBOHIDRATOS_RP,
                Estructure_recomendacion_plan.GRASAS_RP, Estructure_recomendacion_plan.CANTIDAD_RP, Estructure_recomendacion_plan.CONCEPTO_RP};
        return database.query(Estructure_recomendacion_plan.TABLA_RECOMENDACION_PLAN, Vistas, null, null, null, null, null);

    }


    // Proceso calendario para ejercicio
    public void GuardarEjercicioACalendario(String nombre_E, String duracion_E, String event_E,
                                            String time_E, String date_E, String month_E, String year_E,
                                            String notificacion_E, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Estructure_Ejercicios.NOMBRE_E, nombre_E);
        contentValues.put(Estructure_Ejercicios.DURACION_E, duracion_E);
        contentValues.put(Estructure_Ejercicios.EVENT_E, event_E);
        contentValues.put(Estructure_Ejercicios.TIME_E, time_E);
        contentValues.put(Estructure_Ejercicios.DATE_E, date_E);
        contentValues.put(Estructure_Ejercicios.MONTH_E, month_E);
        contentValues.put(Estructure_Ejercicios.YEAR_E, year_E);
        contentValues.put(Estructure_Ejercicios.NOTIFICACION_E, notificacion_E);
        database.insert(Estructure_Ejercicios.TABLA_EJERCICIO, null, contentValues);

    }

    //Guardar ejercicio usuario
    public void GuardarEjercicioSeguimiento(String nombre_RE, String tipo_grado_RE, String calorias_RE,
                                            String MET_RE, String tiempo_RE, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Estructure_recomendacion_ejercicios.NOMBRE_RE, nombre_RE);
        contentValues.put(Estructure_recomendacion_ejercicios.TIPO_GRADO_RE, tipo_grado_RE);
        contentValues.put(Estructure_recomendacion_ejercicios.CALORIAS_RE, calorias_RE);
        contentValues.put(Estructure_recomendacion_ejercicios.MET_RE, MET_RE);
        contentValues.put(Estructure_recomendacion_ejercicios.TIEMPO_RE, tiempo_RE);
        database.insert(Estructure_recomendacion_ejercicios.TABLA_RECOMENDACION_EJERCICIOS, null, contentValues);

    }

    public Cursor ConsultaRecomendacionSeguimientoEjercicio(SQLiteDatabase database){
        String[] Vistas = {Estructure_recomendacion_ejercicios.NOMBRE_RE, Estructure_recomendacion_ejercicios.TIPO_GRADO_RE,
                Estructure_recomendacion_ejercicios.CALORIAS_RE, Estructure_recomendacion_ejercicios.MET_RE,
                Estructure_recomendacion_ejercicios.TIEMPO_RE};
        return database.query(Estructure_recomendacion_ejercicios.TABLA_RECOMENDACION_EJERCICIOS, Vistas, null, null, null, null, null);
    }
    // --------------->
    public void GuardarUsuario(String genero, String peso, String estatura,
                                     String edad, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Estructure_usuario.GENERO_U, genero);
        contentValues.put(Estructure_usuario.PESO_U, peso);
        contentValues.put(Estructure_usuario.ESTATURA_U, estatura);
        contentValues.put(Estructure_usuario.EDAD_U, edad);
        database.insert(Estructure_usuario.TABLA_USUARIO, null, contentValues);

    }
  public Cursor ConsultaPeso(SQLiteDatabase database){
        String[] Vistas = {Estructure_usuario.PESO_U, Estructure_usuario.ESTATURA_U, Estructure_usuario.EDAD_U};
        return database.query(Estructure_usuario.TABLA_USUARIO, Vistas, null, null, null, null, null);
        }

    // --------------->
    public void GuardarPerfilUsuarioRequerimientos(String kilocalorias_R, String proteinas_R,
                                                   String carbohidratos_R,
                                            String grasas_R, String basal_R, String agua_R, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Estructure_requerimientos.KILOCALORIAS_R, kilocalorias_R);
        contentValues.put(Estructure_requerimientos.PROTEINAS_R, proteinas_R);
        contentValues.put(Estructure_requerimientos.CARBOHIDRATOS_R, carbohidratos_R);
        contentValues.put(Estructure_requerimientos.GRASAS_R, grasas_R);
        contentValues.put(Estructure_requerimientos.BASAL_R, basal_R);
        contentValues.put(Estructure_requerimientos.AGUA_R, agua_R);
        database.insert(Estructure_requerimientos.TABLA_REQUERIMIENTOS, null, contentValues);

    }
    public Cursor ConsultaPerfilRequerimientos(SQLiteDatabase database){
        String[] Vistas = {Estructure_requerimientos.KILOCALORIAS_R, Estructure_requerimientos.PROTEINAS_R, Estructure_requerimientos.CARBOHIDRATOS_R,
                Estructure_requerimientos.GRASAS_R, Estructure_requerimientos.BASAL_R, Estructure_requerimientos.AGUA_R};
        return database.query(Estructure_requerimientos.TABLA_REQUERIMIENTOS, Vistas, null, null, null, null, null);
    }
    // --------------->
    public void GuardarPerfilUsuarioRequerimientosGramos(String proteinas_G, String carbohidratos_G, String grasas_G,
                                                   SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Estructure_requerimiento_gramos.PROTEINAS_G, proteinas_G);
        contentValues.put(Estructure_requerimiento_gramos.CARBOHIDRATOS_G, carbohidratos_G);
        contentValues.put(Estructure_requerimiento_gramos.GRASAS_G, grasas_G);

        database.insert(Estructure_requerimiento_gramos.TABLA_REQUERIMIENTO_GRAMOS, null, contentValues);

    }

    public Cursor ConsultaPerfilRequerimientosGramos(SQLiteDatabase database){
        String[] Vistas = {Estructure_requerimiento_gramos.PROTEINAS_G, Estructure_requerimiento_gramos.CARBOHIDRATOS_G, Estructure_requerimiento_gramos.GRASAS_G};
        return database.query(Estructure_requerimiento_gramos.TABLA_REQUERIMIENTO_GRAMOS, Vistas, null, null, null, null, null);
    }


    // --------------->
    public Cursor LeyendoEjercicio(String date_E, SQLiteDatabase database) {
        String[] Vistas = {Estructure_Ejercicios.NOMBRE_E, Estructure_Ejercicios.DURACION_E,
                Estructure_Ejercicios.EVENT_E, Estructure_Ejercicios.TIME_E, Estructure_Ejercicios.DATE_E,
                Estructure_Ejercicios.MONTH_E, Estructure_Ejercicios.YEAR_E};
        String Seleccion = Estructure_Ejercicios.DATE_E + "=?";
        String[] SeleccionArgs = {date_E};

        return database.query(Estructure_Ejercicios.TABLA_EJERCICIO, Vistas, Seleccion, SeleccionArgs, null, null, null);
    }

    //
    public Cursor LeyendoEjercicioPorMes(String month_E, String year_E, SQLiteDatabase database) {
        String[] Vistas = {Estructure_Ejercicios.NOMBRE_E, Estructure_Ejercicios.DURACION_E,
                Estructure_Ejercicios.EVENT_E, Estructure_Ejercicios.TIME_E, Estructure_Ejercicios.DATE_E,
                Estructure_Ejercicios.MONTH_E, Estructure_Ejercicios.YEAR_E};
        String Seleccion =  Estructure_Ejercicios.MONTH_E + "=? and " + Estructure_Ejercicios.YEAR_E + "=?";
        String[] SeleccionArgs = {month_E, year_E};

        return database.query(Estructure_Ejercicios.TABLA_EJERCICIO, Vistas, Seleccion, SeleccionArgs, null, null, null);
    }


    // Notificacion de ejercicios

    public Cursor LeyendoEjercicioPorIDNotificacion(String nombre_E, String duracion_E, String date_E, String event_E,
                                               String time_E, SQLiteDatabase database){
        String [] Vistas = {Estructure_Ejercicios.ID_E, Estructure_Ejercicios.NOTIFICACION_E};
        String Seleccion = Estructure_Ejercicios.NOMBRE_E+"=? and "+Estructure_Ejercicios.DURACION_E+"=? and "+Estructure_Ejercicios.DATE_E+"=? and "+Estructure_Ejercicios.EVENT_E+"=? and "+Estructure_Ejercicios.TIME_E+"=?";
        String [] SeleccionArgs = {nombre_E, duracion_E, date_E, event_E, time_E};

        return database.query(Estructure_Ejercicios.TABLA_EJERCICIO,Vistas,Seleccion,SeleccionArgs,null,null,null);
    }

    public void actualizarEjercicioPorNotificacion(String nombre_E, String duracion_E, String date_E, String event_E,
                                              String time_E, String notificacion_E,
                                              SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Estructure_Ejercicios.NOTIFICACION_E, notificacion_E);
        String Seleccion = Estructure_Ejercicios.NOMBRE_E+"=? and "+Estructure_Ejercicios.DURACION_E+"=? and "+Estructure_Ejercicios.DATE_E+"=? and "+Estructure_Ejercicios.EVENT_E+"=? and "+Estructure_Ejercicios.TIME_E+"=?";
        String [] SeleccionArgs = {nombre_E, duracion_E, date_E, event_E, time_E};
        database.update(Estructure_Ejercicios.TABLA_EJERCICIO,contentValues,Seleccion,SeleccionArgs);
    }

    // Proceso eliminar ejercicio calendario

    public void EliminarEjercicio(String nombre_E, String duracion_E, String event_E, String time_E, String date_E, SQLiteDatabase database) {
        String Seleccion = Estructure_Ejercicios.NOMBRE_E + "=? and " + Estructure_Ejercicios.DURACION_E + "=? and " + Estructure_Ejercicios.EVENT_E + "=?  and " + Estructure_Ejercicios.TIME_E + "=? and " + Estructure_Ejercicios.DATE_E + "=?";
        String[] SeleccionArg = {nombre_E, duracion_E, event_E, time_E, date_E};
        database.delete(Estructure_Ejercicios.TABLA_EJERCICIO, Seleccion, SeleccionArg);
    }

    //Proceso calendario para comida
    public Cursor LeyendoPlan(String date, SQLiteDatabase database) {
        String[] Vistas = {Estructure_Plan.TIPO_CONCEPTO, Estructure_Plan.NOMBRE_COMIDA,
                Estructure_Plan.EVENT, Estructure_Plan.TIME, Estructure_Plan.DATE, Estructure_Plan.MONTH, Estructure_Plan.YEAR};
        String Seleccion = Estructure_Plan.DATE + "=?";
        String[] SeleccionArgs = {date};

        return database.query(Estructure_Plan.TABLA_PLAN, Vistas, Seleccion, SeleccionArgs, null, null, null);
    }


     // Proceso de calendario para comida
     public Cursor LeyendoPlanPorMes(String month, String year, SQLiteDatabase database){
        String [] Vistas = {Estructure_Plan.TIPO_CONCEPTO, Estructure_Plan.NOMBRE_COMIDA,
                Estructure_Plan.EVENT, Estructure_Plan.TIME, Estructure_Plan.DATE, Estructure_Plan.MONTH, Estructure_Plan.YEAR};
        String Seleccion = Estructure_Plan.MONTH +"=? and "+Estructure_Plan.YEAR+"=?";
        String [] SeleccionArgs = {month, year};

        return database.query(Estructure_Plan.TABLA_PLAN,Vistas,Seleccion,SeleccionArgs,null,null,null);
    }
    //Proceso para eliminar Plan
    public void EliminarPlan(String tipo_concepto, String nombre_comida, String event, String time, String date, SQLiteDatabase database){
           String Seleccion = Estructure_Plan.TIPO_CONCEPTO+"=? and "+Estructure_Plan.NOMBRE_COMIDA+"=? and "+Estructure_Plan.EVENT+"=? and "+Estructure_Plan.TIME+"=? and "+Estructure_Plan.DATE+"=?";
           String[] SeleccionArg = {tipo_concepto,nombre_comida,event, time, date};
           database.delete(Estructure_Plan.TABLA_PLAN, Seleccion, SeleccionArg);
    }

    public Cursor LeyendoPlanPorIDNotificacion(String tipo_concepto, String nombre_comida, String date, String event,
                                               String time, SQLiteDatabase database){
        String [] Vistas = {Estructure_Plan.ID, Estructure_Plan.NOTIFICACION};
        String Seleccion = Estructure_Plan.TIPO_CONCEPTO+"=? and "+Estructure_Plan.NOMBRE_COMIDA+"=? and "+Estructure_Plan.DATE+"=? and "+Estructure_Plan.EVENT+"=? and "+Estructure_Plan.TIME+"=?";
        String [] SeleccionArgs = {tipo_concepto, nombre_comida, date, event, time};

        return database.query(Estructure_Plan.TABLA_PLAN,Vistas,Seleccion,SeleccionArgs,null,null,null);
    }

    public void actualizarPlanPorNotificacion(String tipo_concepto, String nombre_comida, String date, String event,
                                              String time, String notificacion,
                                              SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Estructure_Plan.NOTIFICACION, notificacion);
        String Seleccion = Estructure_Plan.TIPO_CONCEPTO+"=? and "+Estructure_Plan.NOMBRE_COMIDA+"=? and "+Estructure_Plan.DATE+"=? and "+Estructure_Plan.EVENT+"=? and "+Estructure_Plan.TIME+"=?";
        String [] SeleccionArgs = {tipo_concepto, nombre_comida, date, event, time};
        database.update(Estructure_Plan.TABLA_PLAN,contentValues,Seleccion,SeleccionArgs);
    }
}
