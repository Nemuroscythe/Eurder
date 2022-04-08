package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.item.domain.Item;

import java.time.LocalDate;
import java.util.Objects;

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
            return LocalDate.now().plusDays(DELIVERY_DELAY_WHEN_NOT_ENOUGH_STOCK);
        }
        return LocalDate.now().plusDays(DELIVERY_DELAY_WHEN_IN_STOCK);
    }

    public double calculateItemGroupTotalPrice(){
        return amount * item.getPrice();
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemGroup itemGroup = (ItemGroup) o;
        return amount == itemGroup.amount && Objects.equals(item, itemGroup.item) && Objects.equals(shippingDate, itemGroup.shippingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, amount, shippingDate);
    }
}