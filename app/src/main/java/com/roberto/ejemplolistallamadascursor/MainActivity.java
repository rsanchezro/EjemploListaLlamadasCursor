package com.roberto.ejemplolistallamadascursor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.AsyncQueryHandler;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.CallLog.Calls;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MiAdaptador adaptador;
    RecyclerView mirecycler;
    LlamadaViewModel llamadaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mirecycler=findViewById(R.id.mireciclerview);
        adaptador= new MiAdaptador(this,R.layout.elementolista);
        llamadaViewModel= new ViewModelProvider(this).get(LlamadaViewModel.class);
        llamadaViewModel.cargarListaLlamadas(this,Calls.INCOMING_TYPE);
        llamadaViewModel.obtenerLlamadas().observe(this, new Observer<ArrayList<Llamada>>() {
            @Override
            public void onChanged(ArrayList<Llamada> llamadas) {
                adaptador.setLista_llamadas(llamadas);
            }
        });

        mirecycler.setLayoutManager(new LinearLayoutManager(this));
        mirecycler.setAdapter(adaptador);



        //LO primero que voy a hacer es solicitar los permisos
        //Obtengo las llamadas entrantes


    //  CursorProvider c=new CursorProvider(this);

     // CargarReciclerView(c.cargarListaLlamadasAsinc(Calls.INCOMING_TYPE));


    }

    private void CargarReciclerView(ArrayList<Llamada> llamadas) {
        RecyclerView r=findViewById(R.id.mireciclerview);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(new MiAdaptador(llamadas,this,R.layout.elementolista));
    }



}
