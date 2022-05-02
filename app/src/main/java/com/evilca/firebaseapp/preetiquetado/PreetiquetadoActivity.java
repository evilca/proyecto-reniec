package com.evilca.firebaseapp.preetiquetado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evilca.firebaseapp.MenuActivity;
import com.evilca.firebaseapp.R;
import com.evilca.firebaseapp.modelo.Persona;
import com.evilca.firebaseapp.modelo.Preetiqueta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class PreetiquetadoActivity extends AppCompatActivity {

    Button btnDisminuir, btnAumentar, btnGuardarPreetiqueta, btnDisminuirHasta, btnAumentarHasta;
    int valorDesde = 0, valorHasta = 0;
    TextView lblTextDesde, lblTextHasta;
    AutoCompleteTextView filled_exposed;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String id, valorTipoContenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Preetiquetado");
        setContentView(R.layout.activity_preetiquetado);
        asignarReferencias();
        generarTiposContenedor();
        inicializarFirebase();
    }
    private void inicializarFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    private void generarTiposContenedor(){

        String[] type = new String[]{"Acta de Nacimiento","Acta de Matrimonio","Acta de Defuncion"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,R.layout.drop_down_item, type
        );

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.filled_exposed);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnClickListener(new AdapterView.OnClickListener(){

            @Override
            public void onClick(View view) {
                btnAumentar.setEnabled(true);
                btnDisminuir.setEnabled(true);
                btnAumentarHasta.setEnabled(true);
                btnDisminuirHasta.setEnabled(true);
            }
        });

    }

    private void guardarPreetiqueta(){
        valorTipoContenedor = filled_exposed.getText().toString();

        Preetiqueta p = new Preetiqueta();
        p.setId(UUID.randomUUID().toString());
        p.setTipoContenedor(valorTipoContenedor);
        p.setValorInicialRango(valorDesde);
        p.setValorFinalRango(valorHasta);
        p.setEstado("En Revision");

        Toast.makeText(this, "Preetiqueta Generada", Toast.LENGTH_SHORT).show();
        databaseReference.child("Preetiqueta").child(p.getId()).setValue(p);

        Intent intent = new Intent(PreetiquetadoActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void asignarReferencias() {

    lblTextDesde          = findViewById(R.id.lblTextDesde);
    lblTextHasta          = findViewById(R.id.lblTextHasta);
    btnAumentar           = findViewById(R.id.btnAumentarDesde);
    btnDisminuir          = findViewById(R.id.btnDisminuirDesde);
    btnGuardarPreetiqueta = findViewById(R.id.btnGuardarPreetiqueta);
    filled_exposed        = findViewById(R.id.filled_exposed);
    btnDisminuirHasta     = findViewById(R.id.btnDisminuirHasta);
    btnAumentarHasta      = findViewById(R.id.btnAumentarHasta);

    btnGuardarPreetiqueta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Llamar a Firebase y Guardar el registro
            guardarPreetiqueta();
        }
    });

    btnAumentarHasta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            valorHasta = valorHasta + 1;
            lblTextHasta.setText(String.valueOf(valorHasta));

            if(valorHasta == 1){
                btnDisminuirHasta.setEnabled(true);
            }
            if(valorHasta == 100){
                btnAumentarHasta.setEnabled(false);
            }
        }
    });

    btnDisminuirHasta.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {
        valorHasta = valorHasta - 1;
        lblTextHasta.setText(String.valueOf(valorHasta));
        if(valorHasta == 0){
            btnDisminuirHasta.setEnabled(false);
        }
        if(valorHasta == 99){
            btnAumentarHasta.setEnabled(true);
        }

    }
    });

    btnAumentar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            valorDesde = valorDesde + 1;
                lblTextDesde.setText(String.valueOf(valorDesde));

           if(valorDesde == 1){
               btnDisminuir.setEnabled(true);
           }
           if(valorDesde == 100){
               btnAumentar.setEnabled(false);
           }
        }
    });

    btnDisminuir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            valorDesde = valorDesde - 1;
                lblTextDesde.setText(String.valueOf(valorDesde));
            if(valorDesde == 0){
                btnDisminuir.setEnabled(false);
            }
            if(valorDesde == 99){
                btnAumentar.setEnabled(true);
            }

        }

    });




    }


}