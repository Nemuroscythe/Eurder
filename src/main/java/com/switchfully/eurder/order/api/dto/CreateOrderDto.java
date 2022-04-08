package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.order.domain.ItemGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateOrderDto {

    private String customerId;
    private List<ItemGroup> itemGroupList;

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
