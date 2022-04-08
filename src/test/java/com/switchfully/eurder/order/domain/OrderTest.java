package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.item.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void givenNullCustomerId_WhenCreatingOrder_ThenThrowNullPointerException() {
        //  GIVEN
        Item item = new Item("Bone", "A bone your dog can play with", 5, 10);
        ItemGroup itemGroup = new ItemGroup(item, 2);
        //  WHEN

        //  THEN
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() ->
                        new Order(null, itemGroup));
    }


}