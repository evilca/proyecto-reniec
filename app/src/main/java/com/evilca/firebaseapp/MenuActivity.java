package com.evilca.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.evilca.firebaseapp.preetiquetado.PreetiquetadoActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    Button btnCerrarSesion;
    FirebaseAuth mAuth;
    CardView cvPreetiquetado,cvMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Menu de Opciones");
        setContentView(R.layout.activity_menu);
        mAuth = FirebaseAuth.getInstance();
        asignarReferencias();
    }

    private void asignarReferencias() {
    btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
    cvPreetiquetado = findViewById(R.id.cvPreetiquetado);
    cvMaps          = findViewById(R.id.cvMaps);
    btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        mAuth.signOut();
            Toast.makeText(MenuActivity.this, "Sesion Finalizada", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MenuActivity.this,LoginActivity.class));
        }
    });
    cvPreetiquetado.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MenuActivity.this, PreetiquetadoActivity.class));
        }
    });
    cvMaps.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MenuActivity.this, MapsActivity.class));
        }
    });


    }
}