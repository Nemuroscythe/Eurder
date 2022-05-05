package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.item_group.api.dto.ItemGroupDto;

import java.util.List;
import java.util.UUID;

public class OrderDto {
    private final UUID orderId;
    private final UUID customerId;
    private final List<ItemGroupDto> itemGroupList;
    private final double totalPrice;

    public OrderDto(UUID orderId, UUID customerId, List<ItemGroupDto> itemGroupList, double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.itemGroupList = itemGroupList;
        this.totalPrice = totalPrice;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public List<ItemGroupDto> getItemGroupList() {
        return itemGroupList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
