package com.unicordoba.tiendapp.Repositorios;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.unicordoba.tiendapp.Clases.Producto;
import com.unicordoba.tiendapp.Interfaces.TiendaAppCallback;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProductoRepositorios {

    private static final String COLLECTION_PRODUCTO = "productos";
    private Context miContexto;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<Producto> listado;

    public ProductoRepositorios(Context miContexto) {
        this.miContexto = miContexto;
        this.firebaseFirestore = FirebaseFirestore.getInstance();
        this.listado = new ArrayList<>();
    }

    public void obtenerTodosFS(final TiendaAppCallback<ArrayList<Producto>> response) {
        firebaseFirestore.collection(COLLECTION_PRODUCTO).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if( task.isSuccessful() ){
                    listado.clear();
                    for(DocumentSnapshot item : task.getResult().getDocuments()){
                        Producto producto = item.toObject(Producto.class);
                        listado.add(producto);
                    }
                    response.correcto(listado);
                } else {
                    response.error(task.getException());
                }
            }
        });
    }

    public void escucharTodosFS(final TiendaAppCallback<ArrayList<Producto>> response){

        firebaseFirestore.collection(COLLECTION_PRODUCTO).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                listado.clear();
                for( DocumentSnapshot item : value.getDocuments() ){
                    Producto itemProducto = item.toObject(Producto.class);
                    listado.add(itemProducto);
                }
                response.correcto(listado);
            }
        });
    }

    public void obtenerById(String idProducto, final TiendaAppCallback<Producto> response){

        firebaseFirestore.collection(COLLECTION_PRODUCTO).document(idProducto).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if( task.isSuccessful() ){
                    Producto producto = task.getResult().toObject(Producto.class);
                    response.correcto(producto);
                } else {
                    response.error(task.getException());
                }
            }
        });

    }
}
