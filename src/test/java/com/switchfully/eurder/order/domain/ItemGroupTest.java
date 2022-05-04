package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.infrastructure.exception.NegativeNumberException;
import com.switchfully.eurder.infrastructure.exception.NullItemException;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item_group.domain.ItemGroup;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        double actualTotalPrice = itemGroup.calculateItemGroupTotalPrice();
        //  THEN
        Assertions.assertThat(actualTotalPrice).isEqualTo(expectedTotalPrice);
    }

    @Test
    void givenItemGroupWithEnoughStock_WhenCalculateShippingDate_ThenReturnTodayPlusOne() {
        //  GIVEN
        int givenAmount = 5;
        LocalDate expectedShippingDate = LocalDate.now().plusDays(1);
        Item item = new Item("Bone", "A bone your dog can play with", 3, 10);
        ItemGroup itemGroup = new ItemGroup(item, givenAmount);
        //  WHEN
        LocalDate actualShippingDate = itemGroup.getShippingDate();
        //  THEN
        Assertions.assertThat(actualShippingDate).isEqualTo(expectedShippingDate);
    }

    @Test
    void givenItemGroupWithNotEnoughStock_WhenCalculateShippingDate_ThenReturnTodayPlusSeven() {
        //  GIVEN
        int givenAmount = 5;
        LocalDate expectedShippingDate = LocalDate.now().plusDays(7);
        Item item = new Item("Bone", "A bone your dog can play with", 3, 1);
        ItemGroup itemGroup = new ItemGroup(item, givenAmount);
        //  WHEN
        LocalDate actualShippingDate = itemGroup.getShippingDate();
        //  THEN
        Assertions.assertThat(actualShippingDate).isEqualTo(expectedShippingDate);
    }

    @Test
    void givenNegativeAmount_WhenCreatingAnItemGroup_ThenNegativeNumberException() {
        //  GIVEN
        int givenNegativeAmount = -5;

        Item item = new Item("Bone", "A bone your dog can play with", 3, 1);
        //  THEN
        Assertions.assertThatExceptionOfType(NegativeNumberException.class)
                .isThrownBy(
                        () -> new ItemGroup(item, givenNegativeAmount)
                );
    }

    @Test
    void givenItemNull_WhenCreatingAnItemGroup_ThenNullItemException() {
        //  GIVEN
        int givenAmount = 5;

        //  THEN
        Assertions.assertThatExceptionOfType(NullItemException.class)
                .isThrownBy(
                        () -> new ItemGroup(null, givenAmount)
                );
    }
}