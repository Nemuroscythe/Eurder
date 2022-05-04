package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.order.domain.ItemGroup;

import java.util.List;

public class CreateOrderDto {

    private final String customerId;
    private final List<ItemGroup> itemGroupList;

    public CreateOrderDto(String customerId, List<ItemGroup> itemGroupList) {
        this.customerId = customerId;
        this.itemGroupList = itemGroupList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }
}
