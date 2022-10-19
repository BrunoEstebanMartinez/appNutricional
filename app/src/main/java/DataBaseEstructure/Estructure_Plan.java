package DataBaseEstructure;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Estructure_Plan {

    public static final String TABLA_PLAN = "plan";
    public static final String TIPO_CONCEPTO = "tipo_concepto";
    public static final String NOMBRE_COMIDA = "nombre_comida";
    public static final String EVENT = "planificador_P";
    public static final String TIME = "hora_P";
    public static final String DATE = "dia_P";
    public static final String MONTH = "mes_P";
    public static final String YEAR = "anio_P";
    public static final String ID = "ID";
    public static final String NOTIFICACION = "notificacion";

    public static final String CREAR_TABLA_PLAN = "create table "+TABLA_PLAN+"(ID_plan INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TIPO_CONCEPTO+" VARCHAR, "+NOMBRE_COMIDA+" VARCHAR, "+EVENT+" VARCHAR, "+TIME+" DATETIME, " +DATE+" TEXT, "
            +MONTH+" TEXT, "+YEAR+" TEXT, "+ID+" TEXT, "+NOTIFICACION+" TEXT)";

    public static final String ELIMINAR_TABLA_PLAN = "drop table if exists" + TABLA_PLAN;


}
