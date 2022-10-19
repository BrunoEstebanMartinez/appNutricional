package DataBaseEstructure;

public class Estructure_requerimientos {
    public static final String TABLA_REQUERIMIENTOS = "Requerimientos";
    public static final String KILOCALORIAS_R = "kilocalorias_R";
    public static final String PROTEINAS_R = "proteinas_R";
    public static final String CARBOHIDRATOS_R = "carbohidratos_R";
    public static final String GRASAS_R = "grasas_R";
    public static final String BASAL_R = "basal_R";
    public static final String AGUA_R = "agua_R";

    public static final String CREAR_TABLA_REQUERIMIENTOS = "create table "+TABLA_REQUERIMIENTOS+"(ID_requerimientos INTEGER PRIMARY KEY AUTOINCREMENT, "
            +KILOCALORIAS_R+" VARCHAR, "+PROTEINAS_R+" VARCHAR, "+CARBOHIDRATOS_R+" VARCHAR, "+GRASAS_R+" VARCHAR, "
            +BASAL_R+" VARCHAR, "+AGUA_R+" VARCHAR)";
    public static final String ELIMINAR_TABLA_REQUERIMIENTOS = "drop table if exists" + TABLA_REQUERIMIENTOS;


}
