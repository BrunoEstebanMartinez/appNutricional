package DataBaseEstructure;

import java.io.Serializable;

public class plan_Map implements Serializable {
    protected String TIPO_CONCEPTO, NOMBRE_COMIDA,
            EVENT, TIME, DATE, MONTH, YEAR;

    public plan_Map(String TIPO_CONCEPTO, String NOMBRE_COMIDA, String EVENT,
                    String TIME, String DATE, String MONTH, String YEAR) {
        this.TIPO_CONCEPTO = TIPO_CONCEPTO;
        this.NOMBRE_COMIDA = NOMBRE_COMIDA;
        this.EVENT = EVENT;
        this.TIME = TIME;
        this.DATE = DATE;
        this.MONTH = MONTH;
        this.YEAR = YEAR;
    }

    public String getTIPO_CONCEPTO() {
        return TIPO_CONCEPTO;
    }

    public void setTIPO_CONCEPTO(String TIPO_CONCEPTO) {
        this.TIPO_CONCEPTO = TIPO_CONCEPTO;
    }

    public String getNOMBRE_COMIDA() {
        return NOMBRE_COMIDA;
    }

    public void setNOMBRE_COMIDA(String NOMBRE_COMIDA) {
        this.NOMBRE_COMIDA = NOMBRE_COMIDA;
    }

    public String getEVENT() {
        return EVENT;
    }

    public void setEVENT(String EVENT) {
        this.EVENT = EVENT;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }
}