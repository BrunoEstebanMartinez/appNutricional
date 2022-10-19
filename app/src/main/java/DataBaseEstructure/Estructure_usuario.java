package DataBaseEstructure;

public class Estructure_usuario {
    public static final String TABLA_USUARIO = "usuario";
    public static final String GENERO_U = "genero";
    public static final String PESO_U = "peso";
    public static final String ESTATURA_U = "estatura";
    public static final String EDAD_U = "edad";


    public static final String CREAR_TABLA_USUARIO = "create table "+TABLA_USUARIO+"(ID_usuario INTEGER PRIMARY KEY AUTOINCREMENT, "
            +GENERO_U+" VARCHAR, "+PESO_U+" VARCHAR, "+ESTATURA_U+" VARCHAR, "+EDAD_U+" INT)";



    public static final String ELIMINAR_TABLA_USUARIO = "drop table if exists" + TABLA_USUARIO;
}
