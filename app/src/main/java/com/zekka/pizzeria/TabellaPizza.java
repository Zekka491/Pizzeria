package com.zekka.pizzeria;

import android.provider.BaseColumns;

public interface TabellaPizza extends BaseColumns{

    String nomeTabella = "pizza";
    String nome = "nome_pizza";
    String prezzo = "prezzo_pizza";

    String[] columns = new String[] {
        _ID, nome, prezzo
    };

    String createTablePizza = "CREATE TABLE "
            + nomeTabella + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + nome + " TEXT NOT NULL, " + prezzo + " REAL NOT NULL);";

    String insertTablePizza = "INSERT INTO "
            + nomeTabella + "(" + nome + "," + prezzo + ") VALUES "
            + "('Margherita',5.00),"
            + "('Peperoni',5.00),"
            + "('Diavola',5.00),"
            + "('Fagioli e Cipolla',5.00),"
            + "('Funghi',5.00),"
            + "('Prosciutto',5.00),"
            + "('Romana',5.00),"
            + "('Siciliana',5.00),"
            + "('4 Formaggi',5.50),"
            + "('Rucola e Grana',5.50),"
            + "('4 Stagioni',6.00),"
            + "('Bufala',6.00),"
            + "('Capricciosa',6.00),"
            + "('Prosciutto e Funghi',6.00),"
            + "('Tonno e Cipolla',6.00),"
            + "('Tirolese',6.00),"
            + "('Verdure',6.00),"
            + "('Carbonara',6.00),"
            + "('Calzone',5.00);" ;

    String dropTablePizza = "DROP TABLE IF EXISTS "
            + nomeTabella + ";";

}
