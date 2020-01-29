package com.roberto.ejemplolistallamadascursor;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.Miholder> {
   private ArrayList<Llamada> lista_llamadas;
   private Context c;
   private int resource;
   public MiAdaptador(Context c,int r)
   {
       this.lista_llamadas=null;
       this.c=c;
       this.resource=r;
   }

    public MiAdaptador(ArrayList<Llamada> llamadas,Context c,int r) {
        this.lista_llamadas=llamadas;
        this.c=c;
        this.resource=r;

    }

    @NonNull
    @Override
    public Miholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista_elemento=((AppCompatActivity)c).getLayoutInflater().inflate(this.resource,parent,false);
        return new Miholder(vista_elemento);

    }

    @Override
    public int getItemCount() {
        if(lista_llamadas!=null)
        {
            return lista_llamadas.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull Miholder holder, int position) {
        holder.llamada=lista_llamadas.get(position);
        if(holder.llamada.getFoto()!=null)
        {
            holder.foto.setImageURI(Uri.parse(holder.llamada.getFoto()));
        }
        if(holder.llamada.getNombre()!=null)
        {
            holder.nombre.setText(holder.llamada.getNombre());
        }
        holder.duracion.setText("Duracion: "+holder.llamada.getDuracion()+" sg");
        holder.fecha.setText(holder.llamada.getFecha());
        holder.numero.setText(holder.llamada.getNumero());


    }

    public void setLista_llamadas(ArrayList<Llamada> lista_llamadas) {
        this.lista_llamadas = lista_llamadas;
        notifyDataSetChanged();
    }

    public class Miholder extends RecyclerView.ViewHolder{
        TextView numero;
        TextView nombre;
        TextView duracion;
        TextView fecha;
        ImageView foto;
        Llamada llamada;

        public Miholder(@NonNull View itemView) {
            super(itemView);
            numero=itemView.findViewById(R.id.textView_numero);
            duracion=itemView.findViewById(R.id.textView_duracion);
            fecha=itemView.findViewById(R.id.textView_fecha);
            nombre=itemView.findViewById(R.id.textView_nombre);
            foto=itemView.findViewById(R.id.imageView_foto);

        }
    }
}
