package com.evilca.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button   btnIngresar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Ingreso");
        mAuth = FirebaseAuth.getInstance();
        asignarReferencias();
    }

    private void asignarReferencias() {
        txtEmail    = findViewById(R.id.txtEmail);
        txtEmail.setText("operaciones01@reniec.pe");
        txtPassword = findViewById(R.id.txtPassword);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = txtEmail.getText().toString().trim();
                String passUser  = txtPassword.getText().toString().trim();
                if(emailUser.isEmpty() || passUser.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Es necesario ingresar todos los datos para continuar", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(emailUser, passUser);
                }

            }
        });
    }

    private void loginUser(String emailUser, String passUser) {

        //TODO: EjecutarLogin
        mAuth.signInWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                finish();
                startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                Toast.makeText(LoginActivity.this, "Bienvenido, Operador", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LoginActivity.this, "El usuario o contraseña no existe", Toast.LENGTH_SHORT).show();
            }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error al iniciar Sesion", Toast.LENGTH_SHORT).show();
            }
        });

    }


}