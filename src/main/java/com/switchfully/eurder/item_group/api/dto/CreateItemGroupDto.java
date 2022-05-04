package com.switchfully.eurder.item_group.api.dto;

public class CreateItemGroupDto {
    private final String itemId;
    private final int amount;

    public CreateItemGroupDto(String itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }
}