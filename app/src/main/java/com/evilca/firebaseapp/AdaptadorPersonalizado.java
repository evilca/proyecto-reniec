package com.evilca.firebaseapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evilca.firebaseapp.modelo.Persona;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.VistaHolder> {

    private Context context;
    private List<Persona> listaPersona = new ArrayList<>();


    public AdaptadorPersonalizado(Context context, List<Persona> listaPersona) {
        this.context = context;
        this.listaPersona = listaPersona;
    }

    @NonNull
    @Override
    public AdaptadorPersonalizado.VistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila,parent,false);
        return new VistaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaHolder holder, @SuppressLint("RecyclerView") int position) {
        //Setea los textos del fila.xml
        holder.filaNombres.setText(listaPersona.get(position).getNombres()+" "+listaPersona.get(position).getApellidos());
        holder.filaCorreo.setText(listaPersona.get(position).getCorreo());
        //Funcionalidad del Editar
        holder.filaEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FormActivity.class);
                intent.putExtra("id",listaPersona.get(position).getId());
                intent.putExtra("nombres",listaPersona.get(position).getNombres());
                intent.putExtra("apellidos",listaPersona.get(position).getApellidos());
                intent.putExtra("correo",listaPersona.get(position).getCorreo());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPersona.size();
    }

    public class VistaHolder extends RecyclerView.ViewHolder {
        TextView filaNombres, filaCorreo;
        ImageButton filaEditar, filaEliminar;
        public VistaHolder(@NonNull View itemView) {

            super(itemView);

            filaNombres = itemView.findViewById(R.id.filaNombres);
            filaCorreo = itemView.findViewById(R.id.filaCorreo);
            filaEditar = itemView.findViewById(R.id.filaEditar);
            filaEliminar = itemView.findViewById(R.id.filaEliminar);

        }
    }
}
