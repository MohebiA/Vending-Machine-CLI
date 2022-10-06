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
        if(category.equals("Chip")){
            message = "Crunch Crunch, Yum!";
        }
        else if (category.equals("Candy")){
            message = "Munch Munch, Yum!";
        }
        else if (category.equals("Drink")){
            message = "Glug Glug, Yum!";
        }
        else message = "Chew Chew, Yum!";

        return message;
    }


}
