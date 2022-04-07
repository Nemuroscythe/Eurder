package com.switchfully.eurder.item.domain;

import java.util.UUID;

public class Item {
    private String itemId;
    private String name;
    private String description;
    private double price;
    private int stockAmount;

    public Item (Item originalItem) {
        this.itemId = originalItem.getItemId();
        this.name = originalItem.getName();
        this.description = originalItem.getDescription();
        this.price = originalItem.getPrice();
        this.stockAmount = originalItem.getStockAmount();
    }

    public Item(String name, String description, double price, int stockAmount) {
        this.itemId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockAmount() {
        return stockAmount;
    }
}
