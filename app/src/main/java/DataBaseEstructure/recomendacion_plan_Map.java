package DataBaseEstructure;


import java.io.Serializable;

public class recomendacion_plan_Map implements Serializable {

        protected String nombre_RP;
        protected String tipo_RP;
        protected String numero_unidad_medida_RP;
        protected String unidad_de_medida_RP;
        protected String calorias_RP;
        protected String proteinas_RP;
        protected String carbohidrados_RP;
        protected String grasas_RP;
        protected String cantidad_RP;
        protected String concepto_RP;

        public recomendacion_plan_Map(String nombre_RP, String tipo_RP, String numero_unidad_medida_RP,
                                      String unidad_de_medida_RP, String calorias_RP, String proteinas_RP,
                                      String carbohidrados_RP, String grasas_RP, String cantidad_RP, String concepto_RP) {
            this.nombre_RP = nombre_RP;
            this.tipo_RP = tipo_RP;
            this.numero_unidad_medida_RP = numero_unidad_medida_RP;
            this.unidad_de_medida_RP = unidad_de_medida_RP;
            this.calorias_RP = calorias_RP;
            this.proteinas_RP = proteinas_RP;
            this.carbohidrados_RP = carbohidrados_RP;
            this.grasas_RP = grasas_RP;
            this.cantidad_RP = cantidad_RP;
            this.concepto_RP = concepto_RP;
        }

        public String getNombre_RP() {
            return nombre_RP;
        }

        public void setNombre_RP(String nombre_RP) {
            this.nombre_RP = nombre_RP;
        }

        public String getTipo_RP() {
            return tipo_RP;
        }

        public void setTipo_RP(String tipo_RP) {
            this.tipo_RP = tipo_RP;
        }

        public String getNumero_unidad_medida_RP() {
            return numero_unidad_medida_RP;
        }

        public void setNumero_unidad_medida_RP(String numero_unidad_medida_RP) {
            this.numero_unidad_medida_RP = numero_unidad_medida_RP;
        }

        public String getUnidad_de_medida_RP() {
            return unidad_de_medida_RP;
        }

        public void setUnidad_de_medida_RP(String unidad_de_medida_RP) {
            this.unidad_de_medida_RP = unidad_de_medida_RP;
        }

        public String getCalorias_RP() {
            return calorias_RP;
        }

        public void setCalorias_RP(String calorias_RP) {
            this.calorias_RP = calorias_RP;
        }

        public String getProteinas_RP() {
            return proteinas_RP;
        }

        public void setProteinas_RP(String proteinas_RP) {
            this.proteinas_RP = proteinas_RP;
        }

        public String getCarbohidrados_RP() {
            return carbohidrados_RP;
        }

        public void setCarbohidrados_RP(String carbohidrados_RP) {
            this.carbohidrados_RP = carbohidrados_RP;
        }

        public String getGrasas_RP() {
            return grasas_RP;
        }

        public void setGrasas_RP(String grasas_RP) {
            this.grasas_RP = grasas_RP;
        }

        public String getCantidad_RP() {
            return cantidad_RP;
        }

        public void setCantidad_RP(String cantidad_RP) {
            this.cantidad_RP = cantidad_RP;
        }

        public String getConcepto_RP() {
            return concepto_RP;
        }

        public void setConcepto_RP(String concepto_RP) {
            this.concepto_RP = concepto_RP;
        }
}
