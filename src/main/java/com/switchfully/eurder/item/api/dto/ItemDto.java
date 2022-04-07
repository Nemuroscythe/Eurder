package com.switchfully.eurder.item.api.dto;

public class ItemDto {
    private String itemId;
    private String name;
    private String description;
    private double price;
    private int stockAmount;

    public ItemDto(String itemId, String name, String description, double price, int stockAmount) {
        this.itemId = itemId;
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
