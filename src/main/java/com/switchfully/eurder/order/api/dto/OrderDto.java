package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.item_group.api.dto.ItemGroupDto;
import com.switchfully.eurder.item_group.domain.ItemGroup;

import java.util.List;

public class OrderDto {
    private final String orderId;
    private final String customerId;
    private final List<ItemGroupDto> itemGroupList;
    private final double totalPrice;

    public OrderDto(String orderId, String customerId, List<ItemGroupDto> itemGroupList, double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.itemGroupList = itemGroupList;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ItemGroupDto> getItemGroupList() {
        return itemGroupList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
