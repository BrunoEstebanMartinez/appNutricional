package DataBaseEstructure;

public class Estructure_requerimiento_gramos {
    public static final String TABLA_REQUERIMIENTO_GRAMOS = "requerimiento_gramos";
    public static final String PROTEINAS_G = "proteinas_G";
    public static final String CARBOHIDRATOS_G = "carbohidratos_G";
    public static final String GRASAS_G = "grasas_G";

    public static final String CREAR_TABLA_REQUERIMIENTO_GRAMOS = "create table "+TABLA_REQUERIMIENTO_GRAMOS+"(ID_requerimiento_gramos INTEGER PRIMARY KEY AUTOINCREMENT, "
            +PROTEINAS_G+" VARCHAR, "+CARBOHIDRATOS_G+" VARCHAR, "+GRASAS_G+" VARCHAR)";
    public static final String ELIMINAR_TABLA_REQUERIMIENTO_GRAMOS = "drop table if exists" + TABLA_REQUERIMIENTO_GRAMOS;
}
