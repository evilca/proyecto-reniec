package com.evilca.firebaseapp.modelo;

import com.google.android.gms.maps.model.LatLng;

public class Coordenada {
    private LatLng punto;
    private String titulo;

    public Coordenada(LatLng punto, String titulo) {
        this.punto = punto;
        this.titulo = titulo;
    }

    public LatLng getPunto() {
        return punto;
    }

    public void setPunto(LatLng punto) {
        this.punto = punto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
