package com.zekka.pizzeria;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class PizzaListAdapter extends BaseAdapter {

    ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
    Context context;

    public PizzaListAdapter(Context context, ArrayList<Pizza> arrayList) {
        this.context = context;
        pizzaList = arrayList;
    }

    @Override
    public int getCount() {
        return pizzaList.size();
    }

    @Override
    public Object getItem(int i) {
        return pizzaList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //se non è una view già creala con il suo layout
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_listino,null);
        }

        //otteniamo la pizza alla posizione richiesta
        Pizza pizza = pizzaList.get(position);

        //nome della pizza
        TextView nomePizza = (TextView) view.findViewById(R.id.etichettaPizza);
        nomePizza.setText(pizza.getNamePizza());

        //prezzo della pizza
        TextView prezzoPizza = (TextView) view.findViewById(R.id.etichettaPrezzo);
        prezzoPizza.setText(Double.toString(pizza.getPrice())+"0");

        //ingredienti delle pizze
        TextView ingredientiPizza = (TextView) view.findViewById(R.id.etichettaIngredienti);
        ingredientiPizza.setText(pizza.getIngredients());

        return view;
    }
}