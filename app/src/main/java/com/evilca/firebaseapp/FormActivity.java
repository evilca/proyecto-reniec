package com.evilca.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.evilca.firebaseapp.modelo.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class FormActivity extends AppCompatActivity {

    EditText txtNombres, txtApellidos, txtCorreo;
    Button btnRegistrar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String id;
    Boolean registrar = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        asignarReferencias();
        inicializarFirebase();
        verificarModificar();
    }

    private void verificarModificar() {
        if(getIntent().hasExtra("id")){
            registrar = false;
            id = getIntent().getStringExtra("id");
            txtNombres.setText(getIntent().getStringExtra("nombres"));
            txtApellidos.setText(getIntent().getStringExtra("apellidos"));
            txtCorreo.setText(getIntent().getStringExtra("correo"));

        }
    }

    private void inicializarFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    private void asignarReferencias(){
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCorreo  = findViewById(R.id.txtCorreo);
        btnRegistrar  = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(registrar){
                    registrar();
                }else{
                    actualizar();
                }
            }
        });
    }

    private void actualizar(){

        String nombres = txtNombres.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String correo = txtCorreo.getText().toString();

        HashMap map = new HashMap();
        map.put("nombres", nombres);
        map.put("apellidos", apellidos);
        map.put("correo", correo);
        databaseReference.child("Persona").child(id).updateChildren(map);
        Intent intent = new Intent(FormActivity.this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Persona Actualizada", Toast.LENGTH_SHORT).show();

    }

    private void registrar(){
        String nombres = txtNombres.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String correo = txtCorreo.getText().toString();

        Persona p = new Persona();
        p.setId(UUID.randomUUID().toString());
        p.setNombres(nombres);
        p.setApellidos(apellidos);
        p.setCorreo(correo);

        databaseReference.child("Persona").child(p.getId()).setValue(p);
        Toast.makeText(this, "Persona added", Toast.LENGTH_SHORT).show();
    }

}