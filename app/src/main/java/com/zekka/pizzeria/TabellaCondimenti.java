package com.zekka.pizzeria;

import android.provider.BaseColumns;

public interface TabellaCondimenti extends BaseColumns{

    String nomeTabella = "condimenti";
    String idPizza = "id_pizza";
    String idIngrediente = "id_ing";

    String[] columns = new String[] {
            _ID,idPizza,idIngrediente
    };

    String createTableCondimento = "CREATE TABLE "
            + nomeTabella + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + idPizza + " INTEGER NOT NULL, " + idIngrediente + " INTEGER NOT NULL,"
            + "FOREIGN KEY (" + idPizza + ") REFERENCES " + TabellaPizza.nomeTabella + "(" + TabellaPizza._ID + "),"
            + "FOREIGN KEY (" + idIngrediente + ") REFERENCES " + TabellaIngrediente.nomeTabella + "(" + TabellaIngrediente._ID + "));";

    String insertTableCondimento = "INSERT INTO "
            + nomeTabella + "(" + idPizza + "," + idIngrediente + ") VALUES "
            + "(1,1),(1,2),"
            + "(2,1),(2,2),(2,3),"
            + "(3,1),(3,2),(3,4),"
            + "(4,1),(4,2),(4,5),(4,6),"
            + "(5,1),(5,2),(5,7),"
            + "(6,1),(6,2),(6,8),"
            + "(7,1),(7,2),(7,9),(7,10),"
            + "(8,1),(8,2),(8,9),(8,11),"
            + "(9,1),(9,2),(9,12),(9,13),"
            + "(10,1),(10,2),(10,14),(10,15),"
            + "(11,1),(11,2),(11,8),(11,7),(11,16),"
            + "(12,1),(12,2),(12,17),"
            + "(13,1),(13,2),(13,8),(13,7),(13,16),"
            + "(14,1),(14,2),(14,8),(14,7),"
            + "(15,1),(15,2),(15,18),(15,6),"
            + "(16,1),(16,2),(16,23),"
            + "(17,1),(17,2),(17,19),"
            + "(18,1),(18,2),(18,20),(18,21),(18,22),"
            + "(19,1),(19,2),(19,8),(19,7),(19,16);" ;

    String dropTableCondimento = "DROP TABLE IF EXISTS "
            + nomeTabella + ";";
}
