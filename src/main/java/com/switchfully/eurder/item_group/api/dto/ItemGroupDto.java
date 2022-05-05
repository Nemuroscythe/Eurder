package com.switchfully.eurder.item_group.api.dto;

import com.switchfully.eurder.item.domain.Item;

import java.time.OffsetDateTime;
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
}