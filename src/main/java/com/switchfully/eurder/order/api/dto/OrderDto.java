package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.order.domain.ItemGroup;

public class OrderDto {
    private String orderId;
    private String customerId;
    private ItemGroup itemGroup;
    private double totalPrice;

    public OrderDto(String orderId, String customerId, ItemGroup itemGroup, double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.itemGroup = itemGroup;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}