package com.evilca.firebaseapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.evilca.firebaseapp.modelo.Coordenada;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.evilca.firebaseapp.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    float latitud, longitud;
    List<Coordenada> listaPuntos = new ArrayList<>();
    String titulo;
    boolean varios = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        recuperarDatos();

        if(varios == false ){
            LatLng casa = new LatLng(latitud, longitud);
            mMap.addMarker(new MarkerOptions().position(casa).title(titulo));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(casa,15));
        } else{
            datosVarios();
            for (int i=0; i<listaPuntos.size() ;i++){
                mMap.addMarker(new MarkerOptions()
                       .position(listaPuntos.get(i).getPunto())
                        .title(listaPuntos.get(i).getTitulo())
                );
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(listaPuntos.get(listaPuntos.size()-1).getPunto(),12));
        }
    }

    private void datosVarios() {
    //Desde aqui se puede manejar el retorno de datos para mostrar en el mapa

        listaPuntos.add(new Coordenada(new LatLng(-11.948514,-76.9856886),"Oficina Principal"));
        listaPuntos.add(new Coordenada(new LatLng(-11.959472,-76.9903557),"Estacion del Tren"));
        listaPuntos.add(new Coordenada(new LatLng(-11.985412,-77.0086296),"San Carlos Av"));
    }

    private void recuperarDatos() {
    // Si envian el valor varios
        if(getIntent().hasExtra("varios")){
        varios = true;
        } else {
            latitud = Float.parseFloat(getIntent().getStringExtra("latitud"));
            longitud = Float.parseFloat(getIntent().getStringExtra("longitud"));
            titulo = getIntent().getStringExtra("titulo");
        }
    }



}