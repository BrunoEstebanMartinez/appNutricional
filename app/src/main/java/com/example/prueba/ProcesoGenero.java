package com.example.prueba;

import java.io.Serializable;

public class ProcesoGenero implements Serializable {

    String genero;
    int imagengenero;

    public ProcesoGenero(String genero, int imagengenero) {
        this.genero = genero;
        this.imagengenero = imagengenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getImagengenero() {
        return imagengenero;
    }

    public void setImagengenero(int imagengenero) {
        this.imagengenero = imagengenero;
    }
}
