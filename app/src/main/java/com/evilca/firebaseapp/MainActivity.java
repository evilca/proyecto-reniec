package com.evilca.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.evilca.firebaseapp.modelo.Persona;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    ListView lstPersonas;
    RecyclerView rvPersonas;
    FloatingActionButton btnNuevo;

    //creamos una instancia de Firebase para listar
    FirebaseDatabase fDatabase;
    DatabaseReference dReference;

    //Definición para recibir los datos
    private List<Persona> listaPersonas = new ArrayList<>();
//    ArrayAdapter<Persona> personaArrayAdapter;
    AdaptadorPersonalizado adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
        inicializarFirebase();
        mostrarDatos();
    }

    private void mostrarDatos() {
    dReference.child("Persona").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            listaPersonas.clear();
        for (DataSnapshot item:snapshot.getChildren()){
            Persona p = item.getValue(Persona.class);
            listaPersonas.add(p);
        }
//            personaArrayAdapter = new ArrayAdapter<Persona>(MainActivity.this,android.R.layout.simple_list_item_1,listaPersonas);
//            lstPersonas.setAdapter(personaArrayAdapter);

            adaptador = new AdaptadorPersonalizado(MainActivity.this,listaPersonas);
            rvPersonas.setAdapter(adaptador);
            rvPersonas.setLayoutManager(new LinearLayoutManager(MainActivity.this));

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

    private void asignarReferencias(){
//        lstPersonas = findViewById(R.id.lstPersonas);
        rvPersonas = findViewById(R.id.rvPersonas);
        btnNuevo   = findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });
    }
}