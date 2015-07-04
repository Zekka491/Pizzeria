package com.zekka.pizzeria;

import android.provider.BaseColumns;

public interface TabellaIngrediente extends BaseColumns{

    String nomeTabella = "ingrediente";
    String nome = "nome_ing";
    String prezzo = "prezzo_ing";

    String[] columns = new String[] {
        _ID,nome,prezzo
    };

    String createTableIngrediente = "CREATE TABLE "
            + nomeTabella + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + nome + " TEXT NOT NULL, " + prezzo + " REAL NOT NULL);";

    String insertTableIngrediente = "INSERT INTO "
            + nomeTabella + "(" + nome + "," + prezzo + ") VALUES "
            + "('pomodoro',0.00),"       //1
            + "('mozzarella',0.00),"    //2
            + "('peperoni',0.50),"      //3
            + "('salamino piccante',1.00),"//4
            + "('fagioli',0.50),"       //5
            + "('cipolla',0.50),"       //6
            + "('funghi',0.50),"        //7
            + "('prosciutto',1.00),"    //8
            + "('capperi',0.50),"       //9
            + "('acciughe',0.50),"      //10
            + "('olive',0.50),"         //11
            + "('asiago',0.50),"        //12
            + "('gorgonzola',0.50),"    //13
            + "('rucola',0.50),"        //14
            + "('grana',0.50),"         //15
            + "('carciofini',0.50),"    //16
            + "('mozzarella di bufala',1.50),"//17
            + "('tonno',0.50),"         //18
            + "('verdure miste',1.50)," //19
            + "('panna',0.50),"         //20
            + "('uovo',1.00),"          //21
            + "('pancetta',1.00),"      //22
            + "('speck',1.00);" ;       //23

    String dropTableIngrediente = "DROP TABLE IF EXISTS "
            + nomeTabella + ";";
}
