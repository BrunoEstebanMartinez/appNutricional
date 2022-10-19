package DataBaseEstructure;

import java.io.Serializable;

public class requerimiento_gramos_Map implements Serializable {
    String proteinas_G, carbohidratos_G, grasas_G;

    public requerimiento_gramos_Map(String proteinas_G, String carbohidratos_G, String grasas_G) {
        this.proteinas_G = proteinas_G;
        this.carbohidratos_G = carbohidratos_G;
        this.grasas_G = grasas_G;
    }

    public String getProteinas_G() {
        return proteinas_G;
    }

    public void setProteinas_G(String proteinas_G) {
        this.proteinas_G = proteinas_G;
    }

    public String getCarbohidratos_G() {
        return carbohidratos_G;
    }

    public void setCarbohidratos_G(String carbohidratos_G) {
        this.carbohidratos_G = carbohidratos_G;
    }

    public String getGrasas_G() {
        return grasas_G;
    }

    public void setGrasas_G(String grasas_G) {
        this.grasas_G = grasas_G;
    }
}
