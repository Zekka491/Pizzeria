package com.zekka.pizzeria;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory cursorFactory, int versionDB) {
        super(context,databaseName,cursorFactory,versionDB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //eliminazione tabelle esistenti
        db.execSQL(TabellaCondimenti.dropTableCondimento);
        db.execSQL(TabellaIngrediente.dropTableIngrediente);
        db.execSQL(TabellaPizza.dropTablePizza);

        //creazione tabelle
        db.execSQL(TabellaPizza.createTablePizza);
        db.execSQL(TabellaIngrediente.createTableIngrediente);
        db.execSQL(TabellaCondimenti.createTableCondimento);

        //popolamento tabelle
        db.execSQL(TabellaPizza.insertTablePizza);
        db.execSQL(TabellaIngrediente.insertTableIngrediente);
        db.execSQL(TabellaCondimenti.insertTableCondimento);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
