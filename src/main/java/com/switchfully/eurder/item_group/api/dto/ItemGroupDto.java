package com.switchfully.eurder.item_group.api.dto;

import com.switchfully.eurder.item.domain.Item;

import java.time.LocalDate;
import java.util.Objects;

public class ItemGroupDto {
    private final Item itemSnapshot;
    private final int amount;
    private final LocalDate shippingDate;
    private final double groupPrice;

    public ItemGroupDto(Item itemSnapshot, int amount, LocalDate shippingDate, double groupPrice) {
        this.itemSnapshot = itemSnapshot;
        this.amount = amount;
        this.shippingDate = shippingDate;
        this.groupPrice = groupPrice;
    }

    public Item getItemSnapshot() {
        return itemSnapshot;
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
        ItemGroupDto itemGroup = (ItemGroupDto) o;
        return amount == itemGroup.amount && Objects.equals(itemSnapshot, itemGroup.itemSnapshot) && Objects.equals(shippingDate, itemGroup.shippingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemSnapshot, amount, shippingDate);
    }
}