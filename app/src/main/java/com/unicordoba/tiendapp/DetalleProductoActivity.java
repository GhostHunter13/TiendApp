package com.unicordoba.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.unicordoba.tiendapp.Clases.Producto;

public class DetalleProductoActivity extends AppCompatActivity {

    private TextView etNombre, etDescripcion, etPrecio;
    private ImageView ivImagenProducto;
    private Producto detalleProducto;

    public static final String PRODUCTO_INFO = "producto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        Intent intent = getIntent();
        detalleProducto = (Producto)intent.getSerializableExtra(PRODUCTO_INFO);
        setDatos();
    }

    private void setDatos() {
        etNombre = findViewById(R.id.txtDetalleProductoNombre);
        etDescripcion = findViewById(R.id.txtDetalleProductoDescripcion);
        etPrecio = findViewById(R.id.txtDetalleProductoPrecio);
        ivImagenProducto = findViewById(R.id.ivDetalleProductoImagen);

        etNombre.setText(detalleProducto.getNombre());
        etDescripcion.setText(detalleProducto.getDescripcion());
        etPrecio.setText("$"+detalleProducto.getPrecio());

        Glide.with(this)
                .load(detalleProducto.getUrlImagen())
                .into(ivImagenProducto);

    }
}