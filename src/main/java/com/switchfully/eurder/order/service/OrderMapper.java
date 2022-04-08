package com.switchfully.eurder.order.service;

import com.switchfully.eurder.order.api.dto.CreateOrderDto;
import com.switchfully.eurder.order.api.dto.OrderDto;
import com.switchfully.eurder.order.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toOrder(CreateOrderDto createOrderDto) {
        return new Order(
                createOrderDto.getCustomerId(),
                createOrderDto.getItemGroup()
        );
    }

    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getCustomerId(),
                order.getItemGroup(),
                order.getTotalPrice()
        );
    }
}
