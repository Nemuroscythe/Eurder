package com.switchfully.eurder.item_group.domain;

import com.switchfully.eurder.item.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "item_group")
public class ItemGroup {

    private static final Logger ITEM_GROUP_LOGGER = LoggerFactory.getLogger(ItemGroup.class);
    public static final int DELIVERY_DELAY_WHEN_NOT_ENOUGH_STOCK = 7;
    public static final int DELIVERY_DELAY_WHEN_IN_STOCK = 1;

    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_item_id")
    private Item itemSnapshot;
    @Column(name = "amount")
    private long amount;
    @Column(name = "shipping_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime shippingDate;
    @Column(name = "group_price")
    private double groupPrice;

    public ItemGroup(Item item, long amount) {
        this.itemSnapshot = new Item(item);
        this.amount = amount;
        this.groupPrice = calculateItemGroupTotalPrice();
        shippingDate = calculateShippingDate(item, amount);
    }

    public ItemGroup() {
    }

    private OffsetDateTime calculateShippingDate(Item item, long amount) {
        if (amount > item.getStockAmount()) {
            return OffsetDateTime.now().plusDays(DELIVERY_DELAY_WHEN_NOT_ENOUGH_STOCK);
        }
        return OffsetDateTime.now().plusDays(DELIVERY_DELAY_WHEN_IN_STOCK);
    }

    private double calculateItemGroupTotalPrice() {
        return amount * itemSnapshot.getPrice();
    }

    public UUID getId() {
        return id;
    }

    public Item getItemSnapshot() {
        return itemSnapshot;
    }

    public long getAmount() {
        return amount;
    }

    public double getGroupPrice() {
        return groupPrice;
    }

    public OffsetDateTime getShippingDate() {
        return shippingDate;
    }
}