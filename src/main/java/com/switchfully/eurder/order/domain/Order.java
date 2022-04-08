package com.switchfully.eurder.order.domain;

import java.util.UUID;

public class Order {
    private String orderId;
    private String customerId;
    private ItemGroup itemGroup;
    private double totalPrice;

    public Order(String customerId, ItemGroup itemGroup) {
        this.orderId = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.itemGroup = itemGroup;
        this.totalPrice = calculateTotalPrice(itemGroup);
    }

    private double calculateTotalPrice(ItemGroup itemGroup) {
        return itemGroup.calculateItemGroupTotalPrice();
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
