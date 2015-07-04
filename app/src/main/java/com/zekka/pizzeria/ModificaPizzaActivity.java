package com.zekka.pizzeria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ModificaPizzaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_pizza);

        Bundle extras = getIntent().getExtras();
        Ordine ordine;
        Integer num;

        if(extras != null) {
            String string = extras.getString("callFrom");
            if(string.equals("activityOrdine")) {
                //aperta da ordine per modificare una pizza presente
                ordine = (Ordine) extras.getSerializable("ordine");
                num = extras.getInt("num");

                Toast.makeText(getApplicationContext(), "np=" + ordine.getNumPizze(), Toast.LENGTH_LONG).show();
                ordine.setNumPizze(55);

                Intent intent = new Intent();
                intent.putExtra("ordine", ordine);
                intent.putExtra("num", num);
                setResult(RESULT_OK, intent);
            }
            else {
                //aperta da listino per una nuova pizza
            }
        }
    }
}
