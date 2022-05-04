package com.switchfully.eurder.order.service;

import com.switchfully.eurder.item_group.service.ItemGroupMapper;
import com.switchfully.eurder.order.api.dto.CreateOrderDto;
import com.switchfully.eurder.order.api.dto.OrderDto;
import com.switchfully.eurder.order.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ItemGroupMapper itemGroupMapper;

    public OrderMapper(ItemGroupMapper itemGroupMapper) {
        this.itemGroupMapper = itemGroupMapper;
    }

    public Order toOrder(CreateOrderDto createOrderDto) {
        return new Order(
                createOrderDto.getCustomerId(),
                itemGroupMapper.toItemGroup(createOrderDto.getItemGroupList())
        );
    }

    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getCustomerId(),
                itemGroupMapper.toDto(order.getItemGroupList()),
                order.getTotalPrice()
        );
    }
}