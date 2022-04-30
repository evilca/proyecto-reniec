package com.evilca.firebaseapp.preetiquetado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evilca.firebaseapp.R;

public class PreetiquetadoActivity extends AppCompatActivity {

    Button btnDisminuir, btnAumentar;
    int valor = 0;
    TextView lblTextDesde, lblTextHasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Preetiquetado");
        setContentView(R.layout.activity_preetiquetado);
        asignarReferencias();
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
            }
        });


    }

    private void asignarReferencias() {
    lblTextDesde = findViewById(R.id.lblTextDesde);
    btnAumentar  = findViewById(R.id.btnAumentarDesde);
    btnDisminuir = findViewById(R.id.btnDisminuirDesde);

    btnAumentar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                valor = valor + 1;
                lblTextDesde.setText(String.valueOf(valor));

           if(valor == 1){
               btnDisminuir.setEnabled(true);
           }
           if(valor == 100){
               btnAumentar.setEnabled(false);
           }
        }
    });
    btnDisminuir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                valor = valor - 1;
                lblTextDesde.setText(String.valueOf(valor));
            if(valor == 0){
                btnDisminuir.setEnabled(false);
            }
            if(valor == 99){
                btnAumentar.setEnabled(true);
            }

        }

    });

    }
}