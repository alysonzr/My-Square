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

public class ComentarioAdapter  extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> elementos;



    public ComentarioAdapter(Context context, ArrayList<String > elementos) {
        super(context, R.layout.activity_comentarios, elementos);
        this.context = (Context) context;
        this.elementos = elementos;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable String item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_comentarios, parent, false);


        TextView comentario = (TextView) rowView.findViewById(R.id.comentarios);


        comentario.setText(elementos.get(position));
        return rowView;
    }

}
