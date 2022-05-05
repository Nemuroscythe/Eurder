package com.switchfully.eurder.order.api;

import com.switchfully.eurder.order.api.dto.CreateOrderDto;
import com.switchfully.eurder.order.api.dto.OrderDto;
import com.switchfully.eurder.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService serviceOrder;

    public OrderController(OrderService serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto orderItems(@Valid @RequestBody CreateOrderDto createOrderDto) {
        return serviceOrder.orderItems(createOrderDto);
    }
}