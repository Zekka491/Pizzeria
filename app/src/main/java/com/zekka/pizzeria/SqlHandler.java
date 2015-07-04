package com.zekka.pizzeria;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Collections;

public class SqlHandler {
    public static final String dbName = "pizzeria.db";
    public static final int dbVersion = 3;
    Context context;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper dbHelper;

    public SqlHandler(Context context) {
        dbHelper = new DatabaseHelper(context,dbName,null,dbVersion);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void executeQuery(String query) {
        try {
            if(sqLiteDatabase.isOpen())
                sqLiteDatabase.close();

            sqLiteDatabase = dbHelper.getWritableDatabase();
            sqLiteDatabase.execSQL(query);
        }
        catch(Exception e) {
            System.out.println("DATABASE ERROR" + e);
        }
    }

    public Cursor selectQuery(String query) {
        Cursor cursor = null;
        try {
            if(sqLiteDatabase.isOpen())
                sqLiteDatabase.close();

            if(sqLiteDatabase != null) {
                sqLiteDatabase = dbHelper.getWritableDatabase();
                cursor = sqLiteDatabase.rawQuery(query,null);
            }
            else {
                //
            }
        }
        catch(Exception e) {
            System.out.println("DATABASE ERROR" + e);
        }
        return cursor;
    }
}
