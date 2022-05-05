package com.switchfully.eurder.order.service;

import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.item_group.api.dto.CreateItemGroupDto;
import com.switchfully.eurder.item_group.api.dto.ItemGroupDto;
import com.switchfully.eurder.order.api.dto.CreateOrderDto;
import com.switchfully.eurder.order.api.dto.OrderDto;
import com.switchfully.eurder.order.domain.Order;
import com.switchfully.eurder.order.domain.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, CustomerRepository customerRepository, ItemRepository itemRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    public OrderDto orderItems(CreateOrderDto createOrderDto) {
        customerIdExist(createOrderDto.getCustomerId());
        itemIdExist(createOrderDto.getItemGroupList());
        Order order = orderMapper.toOrder(createOrderDto);
        orderRepository.saveOrder(order);
        return orderMapper.toDto(order);
    }

    private void itemIdExist(List<CreateItemGroupDto> itemGroupList) {
        if (itemGroupList.stream()
                .map(CreateItemGroupDto::getItemId)
                .noneMatch(itemRepository::existsById)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void customerIdExist(UUID customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
