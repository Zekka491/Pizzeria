package com.zekka.pizzeria;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mattia on 08/10/2014.
 */
public class Ordine implements Serializable{
    private Pizza pizza;
    private int numPizze;

    public Ordine() {

    }

    public Ordine(Pizza pizza, int numPizze) {
        this.pizza = pizza;
        this.numPizze = numPizze;
    }

    public int getNumPizze() {
        return numPizze;
    }

    public void setNumPizze(int numPizze) {
        this.numPizze = numPizze;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
