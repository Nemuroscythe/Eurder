package com.switchfully.eurder.item.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID itemId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "stock_amount")
    private long stockAmount;

    public Item(Item originalItem) {
        this.itemId = originalItem.getItemId();
        this.name = originalItem.getName();
        this.description = originalItem.getDescription();
        this.price = originalItem.getPrice();
        this.stockAmount = originalItem.getStockAmount();
    }

    public Item(String name, String description, double price, long stockAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
    }

    public Item() {
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