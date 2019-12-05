package com.example.mypark;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PracaDetalhesAdapter extends ArrayAdapter<String> {

        private final Context context;
        private final ArrayList<String> elementos;

        public PracaDetalhesAdapter(Context context, ArrayList<String> elementos) {
            super(context,  R.layout.activity_lista_instalacoes_custom, elementos);
            this.context = (Context) context;
            this.elementos = elementos;
        }


    @Nullable
    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.activity_lista_instalacoes_custom,parent , false );


            ImageView imageView = (ImageView)rowView.findViewById(R.id.iconInstalacoes);
            TextView Instalacoes = ( TextView )rowView.findViewById (R.id.nomeInstalacoes );



        Instalacoes.setText(elementos.get(position));
        if(elementos.get(position).equalsIgnoreCase("Área para Criança")){
            imageView.setImageResource(R.drawable.area_crianca_icon );
        }if(elementos.get(position).equalsIgnoreCase(" Pista de Corrida")){
            imageView.setImageResource(R.drawable.areadog_icon);
        }if(elementos.get(position).equalsIgnoreCase(" Pista de Ciclismo")){
            imageView.setImageResource(R.drawable.pista_ciclismo_icon);
        }if(elementos.get(position).equalsIgnoreCase(" Academia")){
            imageView.setImageResource(R.drawable.academia_icon);

        }

        return rowView;
    }
}
