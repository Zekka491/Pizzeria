package com.zekka.pizzeria;

/**
 * Created by Mattia on 08/10/2014.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;


public class PizzaListOrdineAdapter extends BaseAdapter {

    ArrayList<Ordine> ordineList = new ArrayList<Ordine>();
    Context context;

    public PizzaListOrdineAdapter(Context context, ArrayList<Ordine> arrayList) {
        this.context = context;
        ordineList = arrayList;
    }

    @Override
    public int getCount() {
        return ordineList.size();
    }

    @Override
    public Object getItem(int i) {
        return ordineList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        //se non è una view già creala con il suo layout
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_ordine,null);
        }

        //otteniamo la pizza alla posizione richiesta
        final Ordine ordine = ordineList.get(position);

        //nome della pizza
        final TextView nomePizza = (TextView) view.findViewById(R.id.txtNomePizza);
        nomePizza.setText(ordine.getPizza().getNamePizza());

        //numero delle pizze
        final Button numPizza = (Button) view.findViewById(R.id.btnModifica);
        numPizza.setTag(ordine);
        numPizza.setText(Integer.toString(ordine.getNumPizze()));
        numPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ModificaPizzaActivity.class);
                intent.putExtra("callFrom","activityOrdine");
                intent.putExtra("ordine",ordine);
                intent.putExtra("num",position);
                ((Activity)context).startActivityForResult(intent,2);
            }
        });

        //prezzo unitario pizze
        final TextView prezzoPizza = (TextView) view.findViewById(R.id.txtPrezzoPizza);
        prezzoPizza.setText("(" + Double.toString(ordine.getPizza().getPrice()) + "0€/cad)");

        return view;
    }
}
