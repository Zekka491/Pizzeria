package com.zekka.pizzeria;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;


public class OrdineActivity extends Activity {

    private int numPizzaDialog = 0;
    private String nomePizzaDialog = "";
    private Double prezzoPizzaDialog = 0.0;
    private ArrayList<Ordine> ordineList;
    private PizzaListOrdineAdapter pizzaListOrdineAdapter;

    private Double tot = 0.00;

    Button btnAggiungiPizza;
    Button btnSvuotaOrdine;
    Button btnModificaPizza;
    TextView etichettaTotale;
    TextView etichettaDescrizione;
    ListView lista;
    SqlHandler sqlHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordine);
        lista =  (ListView)findViewById(R.id.listOrdine);

        btnAggiungiPizza = (Button)findViewById(R.id.btnAggiungiPizza);
        btnSvuotaOrdine = (Button)findViewById(R.id.btnCancella);
        btnModificaPizza = (Button)findViewById(R.id.btnModifica);
        etichettaTotale = (TextView)findViewById(R.id.etichettaTotale);
        etichettaDescrizione = (TextView)findViewById(R.id.etichettaDescrizione);
        sqlHandler = new SqlHandler(this);

        etichettaDescrizione.setText(R.string.ordineVuoto);
        etichettaTotale.setText(Double.toString(tot)+"0");

        btnAggiungiPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrdineActivity.this,ListinoActivity.class);
                intent.putExtra("activity","ordine");
                startActivityForResult(intent, 1);
            }
        });

        btnSvuotaOrdine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etichettaDescrizione.setText(R.string.ordineVuoto);
                tot = 0.00;
                etichettaTotale.setText(Double.toString(tot)+"0");
                ordineList.clear();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //risultato da ListinoActivity
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_CANCELED) {
                    //non si è aggiunta nessuna pizza
                    return;
                }
                else {
                    //si è aggiunta una riga all'ordine
                    numPizzaDialog = data.getIntExtra("nPizza", 0);
                    nomePizzaDialog = data.getStringExtra("nomePizza");
                    prezzoPizzaDialog = data.getDoubleExtra("prezzoPizza", 0.0);
                    //Toast.makeText(getApplicationContext(), numPizzaDialog + " " + nomePizzaDialog, Toast.LENGTH_LONG).show();

                    if (ordineList == null) {
                        //la lista non è ancora stata creata
                        ordineList = new ArrayList<Ordine>();
                        ordineList.clear();
                    }

                    final int indicePizza = cercaPizza(ordineList,nomePizzaDialog);

                    if(indicePizza !=  -1) {
                        //la pizza è già presente nell'ordine

                        final Dialog d = new Dialog(OrdineActivity.this);
                        d.setTitle(R.string.messaggioDialogAggiornaPizze);
                        d.setContentView(R.layout.dialog_conferm);
                        Button btnOk = (Button) d.findViewById(R.id.btnDialogOK);
                        Button btnCancel = (Button) d.findViewById(R.id.btnDialogCancel);

                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //modificare il num sdella pizza aggiungendo il nuovo valore
                                ordineList.get(indicePizza).setNumPizze(ordineList.get(indicePizza).getNumPizze() + numPizzaDialog);
                                //pizzaListOrdineAdapter.notifyDataSetChanged();
                                aggiornaPagina();
                                d.dismiss();
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
                    else {
                        //la pizza non è presente nell'ordine
                        //bisogna recuperarne i dati e inviarli
                        String query = ListaQuery.selectPizza + "'" + nomePizzaDialog + "'";
                        Cursor cursor = sqlHandler.selectQuery(query);

                        if (cursor != null && cursor.getCount() == 1) {
                            if (cursor.moveToFirst()) {
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

                                Ordine ordine = new Ordine(pizza, numPizzaDialog);
                                ordineList.add(ordine);
                            }
                        }
                        if (cursor != null)
                            cursor.close();

                        aggiornaPagina();
                        /*if (pizzaListOrdineAdapter == null) {
                                pizzaListOrdineAdapter = new PizzaListOrdineAdapter(OrdineActivity.this, ordineList);
                                lista.setAdapter(pizzaListOrdineAdapter);
                            } else {
                                pizzaListOrdineAdapter.notifyDataSetChanged();
                        }*/
                    }
                }
                    break;
            case 2:
                if (resultCode == RESULT_CANCELED) {
                    //non si è modificata la pizza
                    return;
                }
                else {
                    Integer num = data.getIntExtra("num",0);
                    Ordine ord = (Ordine) data.getSerializableExtra("ordine");

                    ordineList.get(num).setNumPizze(ord.getNumPizze());
                    ordineList.get(num).setPizza(ord.getPizza());

                    aggiornaPagina();
                }
        }

    }

    public int cercaPizza(ArrayList<Ordine> ordineList, String pizza) {
        int dim = ordineList.size();

        for(int i=0;i<dim;i++) {
            if(ordineList.get(i).getPizza().getNamePizza().equals(pizza))
                return i;
        }

        return -1;
    }

    public void aggiornaPagina() {
        int dimOrdine = ordineList.size();

        if (pizzaListOrdineAdapter == null) {
            pizzaListOrdineAdapter = new PizzaListOrdineAdapter(OrdineActivity.this, ordineList);
            lista.setAdapter(pizzaListOrdineAdapter);
        } else {
            pizzaListOrdineAdapter.notifyDataSetChanged();
        }

        if(dimOrdine >= 0) {
            etichettaDescrizione.setText(R.string.ordineNonVuoto);

            Double totaleOrdine = 0.0;

            for(int i=0;i<dimOrdine;i++) {
                totaleOrdine += ordineList.get(i).getNumPizze()*ordineList.get(i).getPizza().getPrice();
            }

            etichettaTotale.setText(Double.toString(totaleOrdine)+"0");
        }
    }
}
