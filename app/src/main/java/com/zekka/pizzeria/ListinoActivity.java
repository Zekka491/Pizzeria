package com.zekka.pizzeria;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;


public class ListinoActivity extends Activity {

    SqlHandler sqlHandler;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listino);

        Bundle extras = getIntent().getExtras();
        String value;

        if(extras != null) {
            value = extras.getString("activity");

            list = (ListView)findViewById(R.id.listView);

            sqlHandler = new SqlHandler(this);
            showList();

            /*if(value.equals("listino")) {

            }*/

            if(value.equals("ordine")) {
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                        TextView txtNomePizza = (TextView)view.findViewById(R.id.etichettaPizza);
                        final String nomePizza = (String)txtNomePizza.getText();

                        TextView txtPrezzoPizza = (TextView)view.findViewById(R.id.etichettaPrezzo);
                        final Double prezzoPizza = Double.parseDouble((String)txtPrezzoPizza.getText());

                        final Dialog d = new Dialog(ListinoActivity.this);
                        d.setTitle(R.string.messaggioDialogQuantePizze);
                        d.setContentView(R.layout.dialog_num_picker);
                        Button btnOk = (Button)d.findViewById(R.id.btnDialogOK);
                        Button btnCancel = (Button)d.findViewById(R.id.btnDialogCancel);
                        final NumberPicker np = (NumberPicker)d.findViewById(R.id.numberPicker);
                        np.setMaxValue(20);
                        np.setMinValue(1);
                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.putExtra("nPizza",np.getValue());
                                intent.putExtra("nomePizza",nomePizza);
                                intent.putExtra("prezzoPizza",prezzoPizza);
                                setResult(RESULT_OK, intent);
                                d.dismiss();
                                ListinoActivity.this.finish();
                            }
                        });
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                d.dismiss();
                            }
                        });
                        d.show();
                    }
                });
            }
        }
    }

    private void showList() {
        ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
        pizzaList.clear();

        Cursor cursor = sqlHandler.selectQuery(ListaQuery.pizzeListino);

        if(cursor != null && cursor.getCount() != 0) {
            if(cursor.moveToFirst()) {
                do {
                    Pizza pizza = new Pizza();

                    pizza.setId(cursor.getInt(cursor.getColumnIndex(TabellaPizza._ID)));
                    pizza.setNamePizza(cursor.getString(cursor.getColumnIndex(TabellaPizza.nome)));
                    pizza.setPrice(cursor.getDouble(cursor.getColumnIndex(TabellaPizza.prezzo)));

                    Cursor cursorIng = sqlHandler.selectQuery(ListaQuery.ingredientiSingolaListino + pizza.getId() + ";");
                    String ingredienti = "";
                    int conta = 0;
                    if(cursorIng != null && cursorIng.getCount() != 0) {
                        if(cursorIng.moveToFirst()) {
                            do {
                                if(conta==0)
                                    ingredienti += cursorIng.getString(cursorIng.getColumnIndex(TabellaIngrediente.nome));
                                else
                                    ingredienti += ", " + cursorIng.getString(cursorIng.getColumnIndex(TabellaIngrediente.nome));
                                conta++;
                            }while(cursorIng.moveToNext());
                        }
                    }
                    pizza.setIngredients(ingredienti);

                    pizzaList.add(pizza);
                }while(cursor.moveToNext());
            }
        }
        if(cursor != null)
            cursor.close();

        PizzaListAdapter pizzaListAdapter = new PizzaListAdapter(ListinoActivity.this,pizzaList);
        list.setAdapter(pizzaListAdapter);

    }
}
