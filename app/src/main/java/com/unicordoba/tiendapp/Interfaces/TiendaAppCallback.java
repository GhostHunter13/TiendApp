package com.unicordoba.tiendapp.Interfaces;

import com.unicordoba.tiendapp.Clases.Producto;

import java.util.ArrayList;

public interface TiendaAppCallback<T> {

//    void obtenerProductos(ArrayList<Producto> listado);
//
//    void obtenerProductoById(Producto producto);

    void correcto(T respuesta);
    void error(Exception exception);

}
