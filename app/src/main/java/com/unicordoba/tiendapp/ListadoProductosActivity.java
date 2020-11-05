package com.unicordoba.tiendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.unicordoba.tiendapp.Adapters.ProductoAdapter;
import com.unicordoba.tiendapp.Clases.Producto;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListadoProductosActivity extends AppCompatActivity {

    private Button btnCrearProducto;
    private RecyclerView rvListadoProductos;
    private ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_productos);
        listaProductos = new ArrayList<>();
        cargarProductos();
        setDatos();

        rvListadoProductos.setLayoutManager(new LinearLayoutManager(this));
        rvListadoProductos.setHasFixedSize(true);

        ProductoAdapter adapterProductos = new ProductoAdapter(listaProductos);
        rvListadoProductos.setAdapter(adapterProductos);

    }

    private void cargarProductos() {
        Producto p1 = new Producto("PC Asus", 2500, "no URL", "PC Portatil");
        listaProductos.add(p1);

        Producto p2 = new Producto("Disco Duro", 500, "no URL");
        p2.setDescripcion("Disco duro SSD 2TB");
        listaProductos.add(p2);

        Producto p3 = new Producto();
        p3.setNombre("Mouse");
        p3.setPrecio(150);
        p3.setUrlImagen("no Url");
        p3.setDescripcion("Mouse ");
        listaProductos.add(p3);

    }

    private void setDatos() {
        btnCrearProducto = findViewById(R.id.btnCrearProducto);
        rvListadoProductos = findViewById(R.id.rvListaProductos);


        btnCrearProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}