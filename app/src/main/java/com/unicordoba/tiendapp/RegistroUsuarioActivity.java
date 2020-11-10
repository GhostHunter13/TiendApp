package com.unicordoba.tiendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.unicordoba.tiendapp.Clases.Usuario;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private FirebaseAuth firebaseAuth;
    private EditText etNombre, etCorreo,etTelefono, etContraseña, etConfirmarContraseña;
    private Button btnRegistroUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        setDatos();
    }

    private void setDatos() {
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        etContraseña = findViewById(R.id.etContraseña);
        etConfirmarContraseña = findViewById(R.id.etConfirmarContraseña);
    }

    public void crearUsuario(View view) {
        String nombre = etNombre.getText().toString();
        String email = etCorreo.getText().toString();
        String telefono = etTelefono.getText().toString();
        String contraseña = etContraseña.getText().toString();
        String confContraseñ = etConfirmarContraseña.getText().toString();

        final Usuario newUser = new Usuario(nombre, email, telefono);

        firebaseAuth.createUserWithEmailAndPassword(newUser.getCorreo(), contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){
                    firestore.collection("usuarios").document(firebaseAuth.getUid()).set(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if( task.isSuccessful() ){
                                finish();
                                Toast.makeText(RegistroUsuarioActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegistroUsuarioActivity.this, "Hubo un error. "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegistroUsuarioActivity.this, "Error: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}