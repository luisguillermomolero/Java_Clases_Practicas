package com.example;

public class Pedido {

    private String id;
    private String descripcion;

    public Pedido(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
