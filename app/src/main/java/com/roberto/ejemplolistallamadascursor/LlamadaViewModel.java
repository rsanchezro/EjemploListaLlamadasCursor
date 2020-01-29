package com.roberto.ejemplolistallamadascursor;

import android.content.Context;
import android.provider.CallLog;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class LlamadaViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Llamada>> listallamadas;
    private CursorProvider micursor;

    public LlamadaViewModel(Context c,int tipollamada) {
        if (micursor == null) {
            micursor = new CursorProviderAsync(c);
            //Obtenemos la lista de llamadas
            this.listallamadas=micursor.cargarListaLlamadasAsinc(tipollamada);
        }
        if (listallamadas == null) {
                //Cargar la lista de llamadas

        }
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
}
