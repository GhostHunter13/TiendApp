package com.unicordoba.tiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etContraseña;
    private Button btnInicioSesion;
    private Button btnCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDatos();
    }

    private void setDatos() {
        etUsuario = findViewById(R.id.etUsuario);
        etContraseña = findViewById(R.id.etContraseña);
        btnInicioSesion = findViewById(R.id.btnIniciarSesion);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);

        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etUsuario.getText().toString();
                String pass = etContraseña.getText().toString();

                if (user.equals("1") && pass.equals("1")) {
                    Intent intent = new Intent(MainActivity.this, ListadoProductosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Datos Erroneos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistroUsuarioActivity.class);
                startActivity(intent);
            }
        });

    }
}