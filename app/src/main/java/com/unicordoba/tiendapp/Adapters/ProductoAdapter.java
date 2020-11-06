package com.unicordoba.tiendapp.Adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unicordoba.tiendapp.Clases.Producto;
import com.unicordoba.tiendapp.R;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter {

    private ArrayList<Producto> listaProductos;
    private OnItemClickListener onItemClickListener;

    public void setListaProductos(ArrayList<Producto> listaProductos){
        this.listaProductos = listaProductos;
        notifyDataSetChanged();
    }

    public ProductoAdapter(ArrayList<Producto> listadoProductos){
        this.listaProductos = listadoProductos;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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

        public void cargarDatos(final Producto producto){
            tvNombre.setText(producto.getNombre());
            tvDescripcion.setText(producto.getDescripcion());
            tvPrecio.setText("$"+producto.getPrecio());

            Glide.with(itemView.getContext()).load(producto.getUrlImagen()).into(ivProducto);

            if( onItemClickListener != null ){

                tvNombre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClickNombre(producto, getAdapterPosition());
                    }
                });

                tvDescripcion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClickDescripcion(producto, getAdapterPosition());
                    }
                });

                tvPrecio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClickImagen(producto, getAdapterPosition());
                    }
                });

                ivProducto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClickImagen(producto, getAdapterPosition());
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(producto, getAdapterPosition());
                    }
                });

            }

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

    public interface OnItemClickListener{

        void onItemClick(Producto producto, int posicion);
        void onItemClickNombre(Producto producto, int posicion);
        void onItemClickImagen(Producto producto, int posicion);
        void onItemClickDescripcion(Producto producto, int posicion);
        void onItemClicKPrecio(Producto producto, int posicion);

    }

}
