package com.switchfully.eurder.item.domain;

import java.util.Objects;
import java.util.UUID;

public class Item {
    private String itemId;
    private String name;
    private String description;
    private double price;
    private int stockAmount;

    public Item(Item originalItem) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 && stockAmount == item.stockAmount && Objects.equals(itemId, item.itemId) && Objects.equals(name, item.name) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, description, price, stockAmount);
    }
}