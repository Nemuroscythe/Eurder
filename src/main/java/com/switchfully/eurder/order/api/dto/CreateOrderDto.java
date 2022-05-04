package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.item_group.api.dto.CreateItemGroupDto;

import java.util.List;

public class CreateOrderDto {

    private final String customerId;
    private final List<CreateItemGroupDto> itemGroupList;

    public CreateOrderDto(String customerId, List<CreateItemGroupDto> itemGroupList) {
        this.customerId = customerId;
        this.itemGroupList = itemGroupList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<CreateItemGroupDto> getItemGroupList() {
        return itemGroupList;
    }
}
