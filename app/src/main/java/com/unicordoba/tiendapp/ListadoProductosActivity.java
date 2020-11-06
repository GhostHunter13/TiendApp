package com.unicordoba.tiendapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.unicordoba.tiendapp.Adapters.ProductoAdapter;
import com.unicordoba.tiendapp.Clases.Producto;

import org.w3c.dom.Document;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListadoProductosActivity extends AppCompatActivity {

    private Button btnCrearProducto;
    private RecyclerView rvListadoProductos;
    private ArrayList<Producto> listaProductos;
    private FirebaseFirestore firestore;
    private ProductoAdapter adapterProductos;

    public static final String PRODUCTO_INFO = "producto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_productos);
        listaProductos = new ArrayList<>();
        firestore = FirebaseFirestore.getInstance();
//      cargarProductos();
        datosBD();
        onCollectionChange();
        setDatos();

        rvListadoProductos.setLayoutManager(new LinearLayoutManager(this));
        rvListadoProductos.setHasFixedSize(true);

        adapterProductos = new ProductoAdapter(listaProductos);
        adapterProductos.setOnItemClickListener(new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int posicion) {
                Toast.makeText(ListadoProductosActivity.this, producto.getNombre()+" con precio "+ producto.getPrecio(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListadoProductosActivity.this, DetalleProductoActivity.class);
                intent.putExtra(PRODUCTO_INFO, producto);
                startActivity(intent);
            }

            @Override
            public void onItemClickNombre(Producto producto, int posicion) {
                Toast.makeText(ListadoProductosActivity.this, "Nombre "+ producto.getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClickImagen(Producto producto, int posicion) {
                Toast.makeText(ListadoProductosActivity.this, "Imagen "+ producto.getUrlImagen(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClickDescripcion(Producto producto, int posicion) {
                Toast.makeText(ListadoProductosActivity.this, "Descripcion "+ producto.getDescripcion()+ producto.getPrecio(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClicKPrecio(Producto producto, int posicion) {
                Toast.makeText(ListadoProductosActivity.this, "Precio "+ producto.getPrecio(), Toast.LENGTH_SHORT).show();
            }
        });

        rvListadoProductos.setAdapter(adapterProductos);

    }

    private void datosBD() {
        firestore.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if( task.isSuccessful() ){
                    for(DocumentSnapshot item : task.getResult().getDocuments()){
                        Producto producto = item.toObject(Producto.class);
                        listaProductos.add(producto);
                    }
                    adapterProductos.setListaProductos(listaProductos);
                }else {
                    Toast.makeText(ListadoProductosActivity.this, "Hubo un error : "+ task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onCollectionChange(){

        firestore.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                listaProductos.clear();
                for( DocumentSnapshot item : value.getDocuments() ){
                    Producto itemProducto = item.toObject(Producto.class);
                    listaProductos.add(itemProducto);
                }
                adapterProductos.setListaProductos(listaProductos);
            }
        });

    }

    private void cargarProductos() {
        Producto p1 = new Producto("PC Asus", 2500, "https://img2.freepng.es/20180516/jbw/kisspng-laptop-asus-computer-5afc7ce68f8697.3850485815264964865879.jpg", "PC Portatil");
        listaProductos.add(p1);

        Producto p2 = new Producto("Disco Duro", 500, "https://e7.pngegg.com/pngimages/261/707/png-clipart-sandisk-ultra-ii-ssd-solid-state-drive-hard-drives-serial-ata-computer-computer-electronic-device.png");
        p2.setDescripcion("Disco duro SSD 2TB");
        listaProductos.add(p2);

        Producto p3 = new Producto();
        p3.setNombre("Mouse");
        p3.setPrecio(150);
        p3.setUrlImagen("https://www.pngfind.com/pngs/m/247-2473217_gaming-mouse-png-asus-rog-puggio-transparent-png.png");
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