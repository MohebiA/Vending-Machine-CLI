package com.techelevator.view;

public class Item {

    private String name;
    private double price;
    private String slot;
    private String category;
    private String message;

    public Item(String slot, String name, double price, String category) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getMessage() {
        return message;
    }
}
