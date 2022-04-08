package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.order.domain.ItemGroup;

public class CreateOrderDto {

    private String customerId;
    private ItemGroup itemGroup;

    public CreateOrderDto(String customerId, ItemGroup itemGroup) {
        this.customerId = customerId;
        this.itemGroup = itemGroup;
    }

    public String getCustomerId() {
        return customerId;
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }
}
