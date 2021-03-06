package com.roberto.ejemplolistallamadascursor;

import android.Manifest;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.CallLog;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CursorProviderAsync {
    private static MutableLiveData<ArrayList<Llamada>> listallamadas;
    private final String[] campos = {CallLog.Calls.CACHED_PHOTO_URI, CallLog.Calls.CACHED_NAME, CallLog.Calls.DATE, CallLog.Calls.NUMBER, CallLog.Calls.DURATION};
    private Context c;
    public MutableLiveData<ArrayList<Llamada>> obtenerllamadas()
    {
        if(listallamadas==null)
        {
            listallamadas=new MutableLiveData<ArrayList<Llamada>>();
        }
        return listallamadas;

    }
    //Defino un metodo para que las tareas sean asincronas
    public void cargarListaLlamadasAsinc(int tipollamada) {
        if (listallamadas == null) {
            listallamadas = new MutableLiveData<ArrayList<Llamada>>();
        }
        String[] filtro = {new Integer(tipollamada).toString()};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (c.checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                //Solicito permiso
                ((AppCompatActivity) c).requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            }
        }
        CursorProviderAsync.MimanejadorConsultas mimanejadorConsultas=new CursorProviderAsync.MimanejadorConsultas(c.getContentResolver());
        mimanejadorConsultas.startQuery(1,null,CallLog.Calls.CONTENT_URI, campos, "type=?", filtro, "date desc");
        Log.i("Informacion","que pasa");
    }




    public CursorProviderAsync(Context c) {
        this.c = c;


    }


    class MimanejadorConsultas extends AsyncQueryHandler {
        public MimanejadorConsultas(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            if (cursor.getCount() > 0) { //Si hay datos en el cursor
                ArrayList<Llamada> llamadas=new ArrayList<Llamada>();
                while (cursor.moveToNext()) {
                    Llamada l = new Llamada();
                    Log.i("Informacion", CallLog.Calls.CACHED_NAME + "-" + CallLog.Calls.DURATION);
                    l.setNombre(cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)));
                    l.setFoto(cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_PHOTO_URI)));
                    l.setDuracion(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DURATION)));
                    l.setNumero(cursor.getString(
                            cursor.getColumnIndex(CallLog.Calls.NUMBER)));


                    SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy k:mm");
                    l.setFecha(s.format(new Date(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE)))));


                    llamadas.add(l);
                }

                listallamadas.setValue(llamadas);

            }
        }

    }
}
