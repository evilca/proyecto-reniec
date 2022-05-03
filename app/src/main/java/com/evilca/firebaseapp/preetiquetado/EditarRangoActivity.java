package com.evilca.firebaseapp.preetiquetado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.evilca.firebaseapp.AdaptadorPersonalizado;
import com.evilca.firebaseapp.AdaptadorPreetiquetado;
import com.evilca.firebaseapp.MainActivity;
import com.evilca.firebaseapp.R;
import com.evilca.firebaseapp.modelo.Persona;
import com.evilca.firebaseapp.modelo.Preetiqueta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditarRangoActivity extends AppCompatActivity {

    RecyclerView rvRangosPreetiquetado;

    FirebaseDatabase fDatabase;
    DatabaseReference dReference;

    //Definición para recibir los datos
    private List<Preetiqueta> listaPreetiquetas = new ArrayList<>();
    AdaptadorPreetiquetado adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_rango);
        setTitle("Editar Rango");
        asignarReferencias();
        inicializarFirebase();
        mostrarDatos();
    }

    private void mostrarDatos() {
        dReference.child("Preetiqueta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaPreetiquetas.clear();
                for (DataSnapshot item:snapshot.getChildren()){
                    Preetiqueta p = item.getValue(Preetiqueta.class);
                    listaPreetiquetas.add(p);
                }

                adaptador = new AdaptadorPreetiquetado(EditarRangoActivity.this,listaPreetiquetas);
                rvRangosPreetiquetado.setAdapter(adaptador);
                rvRangosPreetiquetado.setLayoutManager(new LinearLayoutManager(EditarRangoActivity.this));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        fDatabase = FirebaseDatabase.getInstance();
        dReference = fDatabase.getReference();
    }

    private void asignarReferencias() {
        rvRangosPreetiquetado = findViewById(R.id.rvRangosPreetiquetado);

    }


}