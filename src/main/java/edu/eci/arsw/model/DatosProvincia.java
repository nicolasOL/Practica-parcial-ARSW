package edu.eci.arsw.model;

public class DatosProvincia {
    private String nombre;
    private String numeroMuertes;
    private String numeroRecuperados;
    private String numeroInfectados;

    public DatosProvincia(String nombre, String numeroMuertes, String numeroRecuperados, String numeroInfectados) {
        this.nombre = nombre;
        this.numeroMuertes = numeroMuertes;
        this.numeroRecuperados = numeroRecuperados;
        this.numeroInfectados = numeroInfectados;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroMuertes() {
        return numeroMuertes;
    }

    public void setNumeroMuertes(String numeroMuertes) {
        this.numeroMuertes = numeroMuertes;
    }

    public String getNumeroRecuperados() {
        return numeroRecuperados;
    }

    public void setNumeroRecuperados(String numeroRecuperados) {
        this.numeroRecuperados = numeroRecuperados;
    }

    public String getNumeroInfectados() {
        return numeroInfectados;
    }

    public void setNumeroInfectados(String numeroInfectados) {
        this.numeroInfectados = numeroInfectados;
    }
}