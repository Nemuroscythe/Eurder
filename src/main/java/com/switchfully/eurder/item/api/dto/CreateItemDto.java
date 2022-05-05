package com.switchfully.eurder.item.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class CreateItemDto {

    @NotBlank(message = "name is null or blank")
    private final String name;
    @NotBlank(message = "name is null or blank")
    private final String description;
    @Positive(message = "price is negative or zero")
    private final double price;
    @PositiveOrZero(message = "stockAmount is negative")
    private final int stockAmount;

    public CreateItemDto(String name, String description, double price, int stockAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
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
