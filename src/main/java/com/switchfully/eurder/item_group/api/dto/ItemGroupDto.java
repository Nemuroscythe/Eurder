package com.switchfully.eurder.item_group.api.dto;

import com.switchfully.eurder.item.domain.Item;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public class ItemGroupDto {
    private final UUID id;
    private final Item itemSnapshot;
    private final long amount;
    private final OffsetDateTime shippingDate;
    private final double groupPrice;

    public ItemGroupDto(UUID id, Item itemSnapshot, long amount, OffsetDateTime shippingDate, double groupPrice) {
        this.id = id;
        this.itemSnapshot = itemSnapshot;
        this.amount = amount;
        this.shippingDate = shippingDate;
        this.groupPrice = groupPrice;
    }

    public UUID getId() {
        return id;
    }
    public Item getItemSnapshot() {
        return itemSnapshot;
    }

    public long getAmount() {
        return amount;
    }

    public OffsetDateTime getShippingDate() {
        return shippingDate;
    }

    public double getGroupPrice() {
        return groupPrice;
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