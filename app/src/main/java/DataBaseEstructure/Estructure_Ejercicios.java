package DataBaseEstructure;

public class Estructure_Ejercicios {
    public static final String TABLA_EJERCICIO = "ejercicio";
    public static final String NOMBRE_E = "nombre_E";
    public static final String DURACION_E = "duracion_E";
    public static final String EVENT_E = "planificador_E";
    public static final String TIME_E= "hora_E";
    public static final String DATE_E = "dia_E";
    public static final String MONTH_E = "mes_E";
    public static final String YEAR_E = "anio_E";
    public static final String ID_E = "ID_E";
    public static final String NOTIFICACION_E = "notificacion_E";


    public static final String CREAR_TABLA_EJERCICIO = "create table "+TABLA_EJERCICIO+"(ID_ejercicio INTEGER PRIMARY KEY AUTOINCREMENT, "
            +NOMBRE_E+" VARCHAR, "+DURACION_E+" VARCHAR, "+EVENT_E+" VARCHAR, "+TIME_E+" DATETIME, " +DATE_E+" TEXT, "
            +MONTH_E+" TEXT, "+YEAR_E+" TEXT, "+ID_E+" TEXT, "+NOTIFICACION_E+" TEXT)";

    public static final String ELIMINAR_TABLA_EJERCICIO = "drop table if exists" + TABLA_EJERCICIO;


}
