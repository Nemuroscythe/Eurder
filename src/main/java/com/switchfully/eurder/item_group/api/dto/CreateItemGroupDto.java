package com.switchfully.eurder.item_group.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

public class CreateItemGroupDto {

    @NotNull(message = "itemId is null")
    private final UUID itemId;
    @Positive(message = "message is negative or zero")
    private final int amount;

    public CreateItemGroupDto(UUID itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public UUID getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }
}