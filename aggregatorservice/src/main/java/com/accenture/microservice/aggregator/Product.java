package com.accenture.microservice.aggregator;

public class Product {

    public String title;
    public int numberInventory;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberInventory() {
        return numberInventory;
    }

    public void setNumberInventory(int numberInventory) {
        this.numberInventory = numberInventory;
    }
}
