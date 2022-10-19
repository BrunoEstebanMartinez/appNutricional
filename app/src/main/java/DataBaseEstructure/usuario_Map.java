package DataBaseEstructure;

import java.io.Serializable;

public class usuario_Map implements Serializable {
    String genero, peso, estatura, edad;


    public usuario_Map(String genero, String peso, String estatura, String edad) {
        this.genero = genero;
        this.peso = peso;
        this.estatura = estatura;
        this.edad = edad;

    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

}