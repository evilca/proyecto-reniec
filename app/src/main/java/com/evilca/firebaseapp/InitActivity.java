package com.evilca.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitActivity extends AppCompatActivity {

    Button btnIngresarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        setTitle("RENIEC");
        asignarReferencias();
    }

    private void asignarReferencias() {
        btnIngresarLogin = findViewById(R.id.btnIngresarLogin);
        btnIngresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InitActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}