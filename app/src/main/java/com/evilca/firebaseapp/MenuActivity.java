package com.evilca.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    Button btnCerrarSesion;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mAuth = FirebaseAuth.getInstance();
        asignarReferencias();
    }

    private void asignarReferencias() {
    btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
    btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        mAuth.signOut();
            Toast.makeText(MenuActivity.this, "Sesion Finalizada", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MenuActivity.this,LoginActivity.class));
        }
    });
    }
}