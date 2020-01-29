package com.roberto.ejemplolistallamadascursor;

import android.content.Context;
import android.provider.CallLog;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class LlamadaViewModel extends ViewModel {
    private static MutableLiveData<ArrayList<Llamada>> listallamadas;
    private CursorProviderAsync micursor;



    public void cargarListaLlamadas(Context c,int tipollamada)
    { if ((micursor == null)){
        micursor = new CursorProviderAsync(c);
        //Obtenemos la lista de llamadas

        //Cargo la lista de llamadas

    }
        listallamadas=micursor.obtenerllamadas();
        micursor.cargarListaLlamadasAsinc(tipollamada);

    }
        public MutableLiveData<ArrayList<Llamada>> obtenerLlamadas()
        {
            return listallamadas;
        }

        public void addLlamadas(ArrayList<Llamada> llamadas)
        {
            listallamadas.setValue(llamadas);
        }

    }

