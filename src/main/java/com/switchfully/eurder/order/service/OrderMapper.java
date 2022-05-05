package com.switchfully.eurder.order.service;

import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.item_group.service.ItemGroupMapper;
import com.switchfully.eurder.order.api.dto.CreateOrderDto;
import com.switchfully.eurder.order.api.dto.OrderDto;
import com.switchfully.eurder.order.domain.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Component
public class OrderMapper {

    private final ItemGroupMapper itemGroupMapper;
    private final CustomerRepository customerRepository;

    public OrderMapper(ItemGroupMapper itemGroupMapper, CustomerRepository customerRepository) {
        this.itemGroupMapper = itemGroupMapper;
        this.customerRepository = customerRepository;
    }

    public Order toOrder(CreateOrderDto createOrderDto) {
        customerExist(createOrderDto.getCustomerId());
        return new Order(
                customerRepository.findById(createOrderDto.getCustomerId()).get(),
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

    private void customerExist(UUID customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}