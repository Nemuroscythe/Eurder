package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.item_group.domain.ItemGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class Order {
    private static final Logger ORDER_LOGGER = LoggerFactory.getLogger(Order.class);

    private final String orderId;
    private final String customerId;
    private final List<ItemGroup> itemGroupList;
    private final double totalPrice;

    public Order(String customerId, List<ItemGroup> itemGroupList) {
        if (customerId == null) {
            ORDER_LOGGER.error("CustomerId is null");
            throw new NullPointerException("Provide a customer id");
        }

        this.orderId = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.itemGroupList = itemGroupList;
        this.totalPrice = calculateTotalPrice(itemGroupList);
    }

    private double calculateTotalPrice(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map(ItemGroup::calculateItemGroupTotalPrice)
                .reduce(0.0, Double::sum);
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
