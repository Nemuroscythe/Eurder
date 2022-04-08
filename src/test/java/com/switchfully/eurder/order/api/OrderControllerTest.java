package com.switchfully.eurder.order.api;

import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.order.api.dto.OrderDto;
import com.switchfully.eurder.order.domain.ItemGroup;
import com.switchfully.eurder.order.domain.Order;
import com.switchfully.eurder.order.service.OrderMapper;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void givenOneItemGroupAndACustomerID_WhenOrderItems_ThenReturnOrderDtoContainingTheOnlyOrderedItemGroup() {
        //  GIVEN
        Customer expectedCustomer = new Customer("Asterix", "TheGallic", "parToutatis@gallic.com", "5, Boarstreet", "0471/00.88.71");
        customerRepository.saveCustomer(expectedCustomer);

        Item expectedItem = new Item("Magic potion", "The famous potion brewed by Panoramix", 1000, 1);
        ItemGroup expectedItemGroup = new ItemGroup(expectedItem, 1);

        Order expectedOrder = new Order(expectedCustomer.getCustomerId(), expectedItemGroup);
        OrderDto expectedOrderDto = orderMapper.toDto(expectedOrder);
        //  WHEN
        OrderDto actualOrderDto = RestAssured
                .given()
                .port(port)
                .body(expectedOrder)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(OrderDto.class);
        //  THEN
        Assertions.assertThat(actualOrderDto.getOrderId()).isNotBlank();
        Assertions.assertThat(actualOrderDto.getCustomerId()).isEqualTo(expectedOrderDto.getCustomerId());
        Assertions.assertThat(actualOrderDto.getItemGroupList()).isEqualTo(expectedOrderDto.getItemGroupList());
        Assertions.assertThat(actualOrderDto.getTotalPrice()).isEqualTo(expectedOrderDto.getTotalPrice());
    }

    @Test
    void givenThreeItemGroupsAndACustomerID_WhenOrderItems_ThenReturnOrderDtoContainingAllOrderedItemGroups() {
        //  GIVEN
        Customer expectedCustomer = new Customer("Asterix", "TheGallic", "parToutatis@gallic.com", "5, Boarstreet", "0471/00.88.71");
        customerRepository.saveCustomer(expectedCustomer);

        Item expectedFirstItem = new Item("Magic potion", "The famous potion brewed by Panoramix", 1000, 1);
        Item expectedSecondItem = new Item("Meatball", "Boar meatballs", 5, 100);
        Item expectedThirdItem = new Item("Menhir", "Huge rock cut by gallics", 200, 10);

        ItemGroup expectedFirstItemGroup = new ItemGroup(expectedFirstItem, 1);
        ItemGroup expectedSecondItemGroup = new ItemGroup(expectedSecondItem, 5);
        ItemGroup expectedThirdItemGroup = new ItemGroup(expectedThirdItem, 3);
        List<ItemGroup> expectedItemGroupList = new ArrayList<>(Arrays.asList(
                expectedFirstItemGroup, expectedSecondItemGroup, expectedThirdItemGroup));

        Order expectedOrder = new Order(expectedCustomer.getCustomerId(), expectedItemGroupList);
        OrderDto expectedOrderDto = orderMapper.toDto(expectedOrder);
        //  WHEN
        OrderDto actualOrderDto = RestAssured
                .given()
                .port(port)
                .body(expectedOrder)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(OrderDto.class);
        //  THEN
        Assertions.assertThat(actualOrderDto.getOrderId()).isNotBlank();
        Assertions.assertThat(actualOrderDto.getCustomerId()).isEqualTo(expectedOrderDto.getCustomerId());
        Assertions.assertThat(actualOrderDto.getItemGroupList()).isEqualTo(expectedOrderDto.getItemGroupList());
        Assertions.assertThat(actualOrderDto.getTotalPrice()).isEqualTo(expectedOrderDto.getTotalPrice());
    }

//    @Test
//    void givenUnknownCustomerId_WhenCreatingOrder_ThenThrowNullPointerException() {
//        //  GIVEN
//        String unknownCustomId = "IAmNotACustomerId";
//        Item item = new Item("Bone", "A bone your dog can play with", 5, 10);
//        ItemGroup itemGroup = new ItemGroup(item, 2);
//        //  WHEN
//
//        //  THEN
//        Assertions.assertThatExceptionOfType(NullPointerException.class)
//                .isThrownBy(() ->
//                        new Order(unknownCustomId, itemGroup));
//    }

}