package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.item.domain.Item;

import java.time.LocalDate;

public class ItemGroup {
    public static final int DELIVERY_DELAY_WHEN_NOT_ENOUGH_STOCK = 7;
    public static final int DELIVERY_DELAY_WHEN_IN_STOCK = 1;
    private Item item;
    private int amount;
    private LocalDate shippingDate;

    public ItemGroup(Item item, int amount) {
        this.item = new Item(item);
        this.amount = amount;
        shippingDate = calculateShippingDate(item, amount);
    }

    private LocalDate calculateShippingDate(Item item, int amount) {
        if (amount > item.getStockAmount()) {
            return LocalDate.now().plusDays(DELIVERY_DELAY_WHEN_IN_STOCK);
        }
        return LocalDate.now().plusDays(DELIVERY_DELAY_WHEN_NOT_ENOUGH_STOCK);
    }

    public double getTotalPrice(){
        return amount * item.getPrice();
    }
}