package com.zekka.pizzeria;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton btnListino = (ImageButton)findViewById(R.id.imageListino);
        ImageButton btnOrdine = (ImageButton)findViewById(R.id.imageOrdine);
        ImageButton btnOrdiniConfermati = (ImageButton)findViewById(R.id.imageOrdiniConfermati);

        btnListino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,ListinoActivity.class);
                intent.putExtra("activity","listino");
                startActivity(intent);
            }
        });

        btnOrdine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,OrdineActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
            .setMessage(R.string.messaggioUscita)
            .setPositiveButton(R.string.si,new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //esci dall'applicazione
                    finishAffinity();
                }
            })
            .setNegativeButton(R.string.no,null)
            .show();
    }
}
