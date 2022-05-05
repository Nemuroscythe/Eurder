package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.item_group.api.dto.CreateItemGroupDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class CreateOrderDto {

    @NotNull(message = "customerId is null")
    private final UUID customerId;
    @Valid
    @NotEmpty(message = "itemGroupList is null or empty")
    private final List<CreateItemGroupDto> itemGroupList;

    public CreateOrderDto(UUID customerId, List<CreateItemGroupDto> itemGroupList) {
        this.customerId = customerId;
        this.itemGroupList = itemGroupList;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public List<CreateItemGroupDto> getItemGroupList() {
        return itemGroupList;
    }
}
