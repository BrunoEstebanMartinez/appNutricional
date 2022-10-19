package DataBaseEstructure;

import java.io.Serializable;

public class recomendacion_ejercicio_Map implements Serializable {
    protected String nombre_RE;
    protected String tipo_grado_RE;
    protected String calorias_RE;
    protected String MET_RE;
    protected String tiempo_RE;


    public recomendacion_ejercicio_Map(String nombre_RE, String tipo_grado_RE,
                                       String calorias_RE, String MET_RE, String
                                               tiempo_RE) {
        this.nombre_RE = nombre_RE;
        this.tipo_grado_RE = tipo_grado_RE;
        this.calorias_RE = calorias_RE;
        this.MET_RE = MET_RE;
        this.tiempo_RE = tiempo_RE;

    }

    public String getNombre_RE() {
        return nombre_RE;
    }

    public void setNombre_RE(String nombre_RE) {
        this.nombre_RE = nombre_RE;
    }

    public String getTipo_grado_RE() {
        return tipo_grado_RE;
    }

    public void setTipo_grado_RE(String tipo_grado_RE) {
        this.tipo_grado_RE = tipo_grado_RE;
    }

    public String getCalorias_RE() {
        return calorias_RE;
    }

    public void setCalorias_RE(String calorias_RE) {
        this.calorias_RE = calorias_RE;
    }

    public String getMET_RE() {
        return MET_RE;
    }

    public void setMET_RE(String MET_RE) {
        this.MET_RE = MET_RE;
    }

    public String getTiempo_RE() {
        return tiempo_RE;
    }

    public void setTiempo_RE(String tiempo_RE) {
        this.tiempo_RE = tiempo_RE;
    }

}
