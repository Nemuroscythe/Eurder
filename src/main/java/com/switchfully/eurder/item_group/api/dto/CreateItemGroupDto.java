package com.switchfully.eurder.item_group.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateItemGroupDto {

    @NotNull(message = "itemId is null")
    private final String itemId;
    @Positive(message = "message is negative or zero")
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