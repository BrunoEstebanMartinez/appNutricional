package DataBaseEstructure;

import java.io.Serializable;

public class requerimientos_Map implements Serializable {
    String kilocalorias_R, proteinas_R, carbohidratos_R, grasas_R, basal_R, agua_R;

    public requerimientos_Map(String kilocalorias_R, String proteinas_R,
                              String carbohidratos_R, String grasas_R, String basal_R, String agua_R) {
        this.kilocalorias_R = kilocalorias_R;
        this.proteinas_R = proteinas_R;
        this.carbohidratos_R = carbohidratos_R;
        this.grasas_R = grasas_R;
        this.basal_R = basal_R;
        this.agua_R = agua_R;
    }

    public String getKilocalorias_R() {
        return kilocalorias_R;
    }

    public void setKilocalorias_R(String kilocalorias_R) {
        this.kilocalorias_R = kilocalorias_R;
    }

    public String getProteinas_R() {
        return proteinas_R;
    }

    public void setProteinas_R(String proteinas_R) {
        this.proteinas_R = proteinas_R;
    }

    public String getCarbohidratos_R() {
        return carbohidratos_R;
    }

    public void setCarbohidratos_R(String carbohidratos_R) {
        this.carbohidratos_R = carbohidratos_R;
    }

    public String getGrasas_R() {
        return grasas_R;
    }

    public void setGrasas_R(String grasas_R) {
        this.grasas_R = grasas_R;
    }

    public String getBasal_R() {
        return basal_R;
    }

    public void setBasal_R(String basal_R) {
        this.basal_R = basal_R;
    }

    public String getAgua_R() {
        return agua_R;
    }

    public void setAgua_R(String agua_R) {
        this.agua_R = agua_R;
    }
}
