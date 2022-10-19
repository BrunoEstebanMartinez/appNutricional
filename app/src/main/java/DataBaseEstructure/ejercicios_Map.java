package DataBaseEstructure;

import java.io.Serializable;

public class ejercicios_Map implements Serializable {
    protected String NOMBRE_E, DURACION_E, EVENT_E, TIME_E, DATE_E, MONTH_E, YEAR_E;

    public ejercicios_Map(String NOMBRE_E, String DURACION_E, String EVENT_E, String TIME_E, String DATE_E, String MONTH_E, String YEAR_E) {
        this.NOMBRE_E = NOMBRE_E;
        this.DURACION_E = DURACION_E;
        this.EVENT_E = EVENT_E;
        this.TIME_E = TIME_E;
        this.DATE_E = DATE_E;
        this.MONTH_E = MONTH_E;
        this.YEAR_E = YEAR_E;
    }

    public String getNOMBRE_E() {
        return NOMBRE_E;
    }

    public void setNOMBRE_E(String NOMBRE_E) {
        this.NOMBRE_E = NOMBRE_E;
    }

    public String getDURACION_E() {
        return DURACION_E;
    }

    public void setDURACION_E(String DURACION_E) {
        this.DURACION_E = DURACION_E;
    }

    public String getEVENT_E() {
        return EVENT_E;
    }

    public void setEVENT_E(String EVENT_E) {
        this.EVENT_E = EVENT_E;
    }

    public String getTIME_E() {
        return TIME_E;
    }

    public void setTIME_E(String TIME_E) {
        this.TIME_E = TIME_E;
    }

    public String getDATE_E() {
        return DATE_E;
    }

    public void setDATE_E(String DATE_E) {
        this.DATE_E = DATE_E;
    }

    public String getMONTH_E() {
        return MONTH_E;
    }

    public void setMONTH_E(String MONTH_E) {
        this.MONTH_E = MONTH_E;
    }

    public String getYEAR_E() {
        return YEAR_E;
    }

    public void setYEAR_E(String YEAR_E) {
        this.YEAR_E = YEAR_E;
    }
}
