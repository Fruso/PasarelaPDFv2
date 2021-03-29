package com.pasarela.model;

public class CuotasModel {

    private String nombre;
    private Integer cantidad;
    private Double precio;

    public CuotasModel(String nombre, Integer cantidad, Double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

  
}
