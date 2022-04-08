package com.switchfully.eurder.order.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    private final Map<String, Object> orderById = new HashMap<>();

    public void saveOrder(Order order) {
        orderById.put(order.getOrderId(), order);
    }
}
