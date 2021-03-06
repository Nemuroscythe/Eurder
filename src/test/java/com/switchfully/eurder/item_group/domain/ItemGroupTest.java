package com.switchfully.eurder.item_group.domain;

import com.switchfully.eurder.item.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

class ItemGroupTest {

    @Test
    void givenItemGroup_WhenCalculateItemGroupTotalPrice_ThenReturnTheProductOfTheItemAmountByThePrice() {
        //  GIVEN
        int givenAmount = 5;
        double givenPrice = 3;
        double expectedTotalPrice = givenAmount * givenPrice;
        Item item = new Item("Bone", "A bone your dog can play with", givenPrice, 10);
        ItemGroup itemGroup = new ItemGroup(item, givenAmount);
        //  WHEN
        double actualTotalPrice = itemGroup.getGroupPrice();
        //  THEN
        Assertions.assertThat(actualTotalPrice).isEqualTo(expectedTotalPrice);
    }

    @Test
    void givenItemGroupWithEnoughStock_WhenCalculateShippingDate_ThenReturnTodayPlusOne() {
        //  GIVEN
        int givenAmount = 5;
        OffsetDateTime expectedShippingDate = OffsetDateTime.now().plusDays(1);
        Item item = new Item("Bone", "A bone your dog can play with", 3, 10);
        ItemGroup itemGroup = new ItemGroup(item, givenAmount);
        //  WHEN
        OffsetDateTime actualShippingDate = itemGroup.getShippingDate();
        //  THEN
        Assertions.assertThat(actualShippingDate.toEpochSecond()).isEqualTo(expectedShippingDate.toEpochSecond());
    }

    @Test
    void givenItemGroupWithNotEnoughStock_WhenCalculateShippingDate_ThenReturnTodayPlusSeven() {
        //  GIVEN
        int givenAmount = 5;
        OffsetDateTime expectedShippingDate = OffsetDateTime.now().plusDays(7);
        Item item = new Item("Bone", "A bone your dog can play with", 3, 1);
        ItemGroup itemGroup = new ItemGroup(item, givenAmount);
        //  WHEN
        OffsetDateTime actualShippingDate = itemGroup.getShippingDate();
        //  THEN
        Assertions.assertThat(actualShippingDate.toEpochSecond()).isEqualTo(expectedShippingDate.toEpochSecond());
    }

}