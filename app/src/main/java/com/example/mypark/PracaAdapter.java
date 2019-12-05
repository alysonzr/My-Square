package com.example.mypark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PracaAdapter extends ArrayAdapter<Praca> {

    private final Context context;
    private final ArrayList<Praca> elementos;


    public PracaAdapter(Context context, ArrayList<Praca> elementos){
        super(context, R.layout.activity_lista_custom,elementos);
        this.context = (Context) context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView , ViewGroup parent ) {
        LayoutInflater inflater = (LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_lista_custom,parent , false );
        TextView nomePraca = ( TextView )rowView.findViewById (R.id.nome );
        TextView instalacoes = ( TextView )rowView.findViewById ( R.id.instalacoes);

        nomePraca.setText ("Nome: "+elementos.get(position).getNome ());
        instalacoes.setText ("Instalações: "+elementos.get (position).getFacilidades ());
        //imagem.setImageResource ( elementos .get (position). getImagem ());

        return rowView ;
    }
}
