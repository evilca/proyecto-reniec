package com.evilca.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.evilca.firebaseapp.preetiquetado.EditarRangoActivity;
import com.evilca.firebaseapp.preetiquetado.PreetiquetadoActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    Button btnCerrarSesion;
    FirebaseAuth mAuth;
    CardView cvPreetiquetado,cvMaps, cvEditarRango, cvCamara;

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
    cvEditarRango   = findViewById(R.id.cvEditarRango);
    cvMaps          = findViewById(R.id.cvMaps);
    cvCamara        = findViewById(R.id.cvCamara);

    btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        mAuth.signOut();
            Toast.makeText(MenuActivity.this, "Sesion Finalizada", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MenuActivity.this,LoginActivity.class));
        }
    });

    cvCamara.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MenuActivity.this, Camara.class);
            startActivity(intent);
        }
    });

    cvEditarRango.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MenuActivity.this, EditarRangoActivity.class);
            startActivity(intent);
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
            Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
//            intent.putExtra("latitud", "-11.948514");
//            intent.putExtra("longitud", "-76.9856886");
//            intent.putExtra("titulo", "Oficina Principal");
            intent.putExtra("varios","1");
            startActivity(intent);
        }
    });


    }
}