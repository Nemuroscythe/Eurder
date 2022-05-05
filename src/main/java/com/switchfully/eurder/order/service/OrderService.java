package com.switchfully.eurder.order.service;

import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.order.api.dto.CreateOrderDto;
import com.switchfully.eurder.order.api.dto.OrderDto;
import com.switchfully.eurder.order.domain.Order;
import com.switchfully.eurder.order.domain.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public OrderDto orderItems(CreateOrderDto createOrderDto) {
        customerIdExist(createOrderDto.getCustomerId());
        Order order = orderMapper.toOrder(createOrderDto);
        orderRepository.saveOrder(order);
        return orderMapper.toDto(order);
    }

    private void customerIdExist(String customerId) {
        if (!customerRepository.exist(customerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
