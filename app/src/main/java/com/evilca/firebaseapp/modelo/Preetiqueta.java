package com.evilca.firebaseapp.modelo;

public class Preetiqueta {
    private String id;
    private String tipoContenedor;
    private int valorInicialRango;
    private int valorFinalRango;
    private String  estado;


    public Preetiqueta(String tipoContenedor, int valorInicialRango, int valorFinalRango, String estado) {
        this.tipoContenedor = tipoContenedor;
        this.valorInicialRango = valorInicialRango;
        this.valorFinalRango = valorFinalRango;
        this.estado = estado;
    }

    public Preetiqueta() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoContenedor() {
        return tipoContenedor;
    }

    public void setTipoContenedor(String tipoContenedor) {
        this.tipoContenedor = tipoContenedor;
    }

    public int getValorInicialRango() {
        return valorInicialRango;
    }

    public void setValorInicialRango(int valorInicialRango) {
        this.valorInicialRango = valorInicialRango;
    }

    public int getValorFinalRango() {
        return valorFinalRango;
    }

    public void setValorFinalRango(int valorFinalRango) {
        this.valorFinalRango = valorFinalRango;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
