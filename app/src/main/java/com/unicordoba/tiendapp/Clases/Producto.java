package com.unicordoba.tiendapp.Clases;

public class Producto {

    private String nombre;
    private int precio;
    private String urlImagen;
    private String descripcion;

    public Producto() {
    }

    public Producto(String nombre, int precio, String urlImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    public Producto(String nombre, int precio, String urlImagen, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
