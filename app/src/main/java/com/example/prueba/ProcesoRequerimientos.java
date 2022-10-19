package com.example.prueba;

import java.io.Serializable;

public class ProcesoRequerimientos implements Serializable {
    String generoGlobal;
    String Estatura;
    String peso;
    String edad;

    public ProcesoRequerimientos(String generoGlobal, String estatura, String peso, String edad) {
        this.generoGlobal = generoGlobal;
        Estatura = estatura;
        this.peso = peso;
        this.edad = edad;
    }

    public String getGeneroGlobal() {
        return generoGlobal;
    }

    public void setGeneroGlobal(String generoGlobal) {
        this.generoGlobal = generoGlobal;
    }

    public String getEstatura() {
        return Estatura;
    }

    public void setEstatura(String estatura) {
        Estatura = estatura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
