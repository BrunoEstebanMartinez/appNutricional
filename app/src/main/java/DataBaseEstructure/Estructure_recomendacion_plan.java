package DataBaseEstructure;

public class Estructure_recomendacion_plan {

    public static final String TABLA_RECOMENDACION_PLAN = "recomendacion_plan";
    public static final String NOMBRE_RP = "nombre_RP";
    public static final String TIPO_RP = "tipo_RP";
    public static final String NUMERO_UNIDAD_MEDIDA_RP = "numero_unidad_medida_RP";
    public static final String UNIDAD_DE_MEDIDA_RP = "unidad_de_medida_RP";
    public static final String CALORIAS_RP = "calorias_RP";
    public static final String PROTEINAS_RP = "proteinas_RP";
    public static final String CARBOHIDRATOS_RP ="carbohidratos_RP";
    public static final String GRASAS_RP = "grasas_RP";
    public static final String CANTIDAD_RP = "cantidad_RP";
    public static final String CONCEPTO_RP = "concepto_RP";



    public static final String CREAR_TABLA_RECOMENDACION_PLAN = "create table "+TABLA_RECOMENDACION_PLAN+"(ID_recomendacion_plan INTEGER PRIMARY KEY AUTOINCREMENT, "+NOMBRE_RP+" VARCHAR, "+TIPO_RP+" VARCHAR, "+NUMERO_UNIDAD_MEDIDA_RP+" INTEGER, "+UNIDAD_DE_MEDIDA_RP+" VARCHAR, "+CALORIAS_RP+" DECIMAL, "+PROTEINAS_RP+" DECIMAL, "+CARBOHIDRATOS_RP+" DECIMAL, "+GRASAS_RP+" DECIMAL, "+CANTIDAD_RP+" INTEGER, "+CONCEPTO_RP+" VARCHAR)";

    public static final String CONSULTA_SUMA = "select sum(calorias_RP) as calorias_RP from recomendacion_plan";

    public static final String ELIMINAR_TABLA_RECOMENDACIONES_PLAN = "drop table if exists" +TABLA_RECOMENDACION_PLAN;
}
