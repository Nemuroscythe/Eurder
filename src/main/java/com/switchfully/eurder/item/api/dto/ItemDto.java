package com.switchfully.eurder.item.api.dto;

import java.util.UUID;

public class ItemDto {
    private final UUID itemId;
    private final String name;
    private final String description;
    private final double price;
    private final long stockAmount;

    public ItemDto(UUID itemId, String name, String description, double price, long stockAmount) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
    }

    public UUID getItemId() {
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

    public long getStockAmount() {
        return stockAmount;
    }
}
