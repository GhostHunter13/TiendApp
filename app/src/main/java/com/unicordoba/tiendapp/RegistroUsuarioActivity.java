package com.unicordoba.tiendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.unicordoba.tiendapp.Clases.Usuario;

public class RegistroUsuarioActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        final Usuario newUser = new Usuario("Pierre", "pierre.laurens1210@gmail.com", "3043936242");

        firebaseAuth.createUserWithEmailAndPassword(newUser.getCorreo(), "123456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){
                    firestore.collection("usuarios").document(firebaseAuth.getUid()).set(newUser);
                }
            }
        });



    }
}