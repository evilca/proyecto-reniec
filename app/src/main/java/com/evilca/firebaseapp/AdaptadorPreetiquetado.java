package com.evilca.firebaseapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evilca.firebaseapp.modelo.Persona;
import com.evilca.firebaseapp.modelo.Preetiqueta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPreetiquetado extends RecyclerView.Adapter<AdaptadorPreetiquetado.VistaHolder>{


    private Context context;
    private List<Preetiqueta> listaPreetiquetas = new ArrayList<>();

    public AdaptadorPreetiquetado(Context context, List<Preetiqueta> listaPreetiquetas) {
        this.context = context;
        this.listaPreetiquetas = listaPreetiquetas;
    }


    @NonNull
    @Override
    public AdaptadorPreetiquetado.VistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila_preetiqueta,parent,false);
        return new AdaptadorPreetiquetado.VistaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPreetiquetado.VistaHolder holder, int position) {
        holder.filaTipo.setText(listaPreetiquetas.get(position).getTipoContenedor());
        holder.filaInicial.setText(String.valueOf(listaPreetiquetas.get(position).getValorInicialRango() + " - "+ listaPreetiquetas.get(position).getValorFinalRango()));
        holder.filaEstado.setText(listaPreetiquetas.get(position).getEstado());


//        holder.filaEditar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, FormActivity.class);
//                intent.putExtra("id",listaPersona.get(position).getId());
//                intent.putExtra("nombres",listaPersona.get(position).getNombres());
//                intent.putExtra("apellidos",listaPersona.get(position).getApellidos());
//                intent.putExtra("correo",listaPersona.get(position).getCorreo());
//                context.startActivity(intent);
//            }
//        });
//        holder.filaEliminar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                eliminar(listaPersona.get(position).getId());
//            }
//        });
    }

    private void eliminar(String id) {
//        AlertDialog.Builder ventana = new AlertDialog.Builder(context);
//        ventana.setTitle("Eliminar");
//        ventana.setMessage("¿Desea Eliminar?");
//        ventana.setNegativeButton("No", null);
//        ventana.setPositiveButton("Si", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                FirebaseDatabase fDatabase;
//                DatabaseReference dReference;
//                FirebaseApp.initializeApp(context);
//                fDatabase = FirebaseDatabase.getInstance();
//                dReference = fDatabase.getReference();
//                //Elimina el id de la tabla Persona
//                dReference.child("Persona").child(id).removeValue();
//            }
//        });
//
//        ventana.create().show();
    }

    @Override
    public int getItemCount() {
        return listaPreetiquetas.size();
    }

    public class VistaHolder extends RecyclerView.ViewHolder {
        TextView filaTipo, filaInicial, filaFinal, filaEstado;
//        ImageButton filaEditarRango, filaEliminarRango;
        public VistaHolder(@NonNull View itemView) {
            super(itemView);
            filaTipo     = itemView.findViewById(R.id.filaTipo);
            filaEstado  = itemView.findViewById(R.id.filaEstado);
            filaInicial  = itemView.findViewById(R.id.filaInicial);
//            filaEditar   = itemView.findViewById(R.id.filaEditarRango);
//            filaEliminar = itemView.findViewById(R.id.filaEliminarRango);

        }
    }




}
