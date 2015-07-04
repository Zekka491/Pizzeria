package com.zekka.pizzeria;

/**
 * Created by Mattia on 06/10/2014.
 */
public interface ListaQuery {

    String pizzeListino = "SELECT * FROM " + TabellaPizza.nomeTabella + " ORDER BY " + TabellaPizza.nome + ";";
    
    String ingredientiSingolaListino = "SELECT " + TabellaIngrediente.nome + " FROM " + TabellaIngrediente.nomeTabella + " JOIN "
            + TabellaCondimenti.nomeTabella + " ON " + TabellaCondimenti.idIngrediente + " = "
            + TabellaIngrediente.nomeTabella + "." + TabellaIngrediente._ID + " WHERE " + TabellaCondimenti.idPizza + " = ";


    String selectPizza = "SELECT * FROM " + TabellaPizza.nomeTabella + " WHERE " + TabellaPizza.nome + " = ";
    
    //possono essere dei metodi cosi da ottenere il completamento diretto senza aggiunte sul codice
}
