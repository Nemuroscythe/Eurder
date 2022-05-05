package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.item_group.domain.ItemGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order")
public class Order {
    private static final Logger ORDER_LOGGER = LoggerFactory.getLogger(Order.class);

    @Id
    @GeneratedValue
    private UUID orderId;
    @OneToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;
    @OneToMany
    @JoinColumn(name = "fk_order_id")
    private List<ItemGroup> itemGroupList;
    @Column(name = "total_price")
    private double totalPrice;

    public Order(Customer customer, List<ItemGroup> itemGroupList) {
        this.orderId = UUID.randomUUID();
        this.customer = customer;
        this.itemGroupList = itemGroupList;
        this.totalPrice = calculateTotalPrice(itemGroupList);
    }

    public Order() {
    }

    private double calculateTotalPrice(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map(ItemGroup::getGroupPrice)
                .reduce(0.0, Double::sum);
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustomerId() {
        return customer.getCustomerId();
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
