package com.zekka.pizzeria;

import java.io.Serializable;

public class Pizza implements Serializable{
    private int id;
    private String namePizza;
    private Double price;
    private String ingredients;

    public Integer getId() {
        return id;
    }

    public void setId(int _id) {
        id = _id;
    }

    public String getNamePizza() {
        return namePizza;
    }

    public void setNamePizza(String _namePizza) {
        this.namePizza = _namePizza;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double _price) {
        price = _price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String _ingredients) {
        this.ingredients = _ingredients;
    }
}
