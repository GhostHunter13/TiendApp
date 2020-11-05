package com.unicordoba.tiendapp.Adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicordoba.tiendapp.Clases.Producto;
import com.unicordoba.tiendapp.R;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter {

    private ArrayList<Producto> listaProductos;

    public ProductoAdapter(ArrayList<Producto> listadoProductos){
        this.listaProductos = listadoProductos;
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivProducto;
        private TextView tvNombre;
        private TextView tvDescripcion;
        private TextView tvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProducto = itemView.findViewById(R.id.ivProducto);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);


        }

        public void cargarDatos(Producto producto){
            tvNombre.setText(producto.getNombre());
            tvDescripcion.setText(producto.getDescripcion());
            tvPrecio.setText("$"+producto.getPrecio());
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItemProducto = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(vistaItemProducto);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        ProductoViewHolder productoViewHolder = (ProductoViewHolder) holder;
        productoViewHolder.cargarDatos(producto);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }
}
