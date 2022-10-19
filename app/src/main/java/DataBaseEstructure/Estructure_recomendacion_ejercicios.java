package DataBaseEstructure;

public class Estructure_recomendacion_ejercicios {
    public static final String TABLA_RECOMENDACION_EJERCICIOS = "recomendacion_ejercicio";
    public static final String NOMBRE_RE = "nombre_RE";
    public static final String TIPO_GRADO_RE = "tipo_grado_RE";
    public static final String CALORIAS_RE = "calorias_RE";
    public static final String MET_RE = "MET_RE";
    public static final String TIEMPO_RE = "tiempo_RE";

    public static final String CREAR_TABLA_RECOMENDACION_EJERCICIOS = "create table "+TABLA_RECOMENDACION_EJERCICIOS+"(ID_recomendacion_ejercicio INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NOMBRE_RE+" VARCHAR, "+TIPO_GRADO_RE+" VARCHAR, "+CALORIAS_RE+" DECIMAL, "+
            MET_RE+" DECIMAL, "+TIEMPO_RE+" INT)";

    public static final String ELIMINAR_TABLA_RECOMENDACION_EJERCICIOS = "drop table if exists" +TABLA_RECOMENDACION_EJERCICIOS;
}
