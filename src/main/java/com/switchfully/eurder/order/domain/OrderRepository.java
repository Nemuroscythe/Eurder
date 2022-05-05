package com.switchfully.eurder.order.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderRepository {
    private final Map<UUID, Object> orderById = new HashMap<>();

    public void saveOrder(Order order) {
        orderById.put(order.getOrderId(), order);
    }
}
