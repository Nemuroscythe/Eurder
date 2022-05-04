package com.switchfully.eurder.order.api;

import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.item_group.api.dto.CreateItemGroupDto;
import com.switchfully.eurder.order.api.dto.CreateOrderDto;
import com.switchfully.eurder.order.api.dto.OrderDto;
import com.switchfully.eurder.order.service.OrderMapper;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class OrderControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;

    private Item itemInDB;
    private Customer customerInDB;

    @BeforeEach
    void setUp() {
        customerInDB = new Customer("Asterix", "TheGallic", "parToutatis@gallic.com", "5, Boarstreet", "0471/00.88.71");
        customerRepository.saveCustomer(customerInDB);


        itemInDB = new Item("Magic potion", "The famous potion brewed by Panoramix", 1000, 1);
        itemRepository.saveItem(itemInDB);
    }

    @Test
    void givenOneItemGroupAndACustomerID_WhenOrderItems_ThenReturnOrderDtoContainingTheOnlyOrderedItemGroup() {
        //  GIVEN
        CreateItemGroupDto expectedCreateItemGroupDto = new CreateItemGroupDto(itemInDB.getItemId(), 1);
        CreateOrderDto expectedCreateOrderDto = new CreateOrderDto(customerInDB.getCustomerId(), List.of(expectedCreateItemGroupDto));
        double expectedPrice = itemInDB.getPrice() * expectedCreateItemGroupDto.getAmount();
        //  WHEN
        OrderDto actualOrderDto = RestAssured
                .given()
                .port(port)
                .body(expectedCreateOrderDto)
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
        Assertions.assertThat(actualOrderDto.getCustomerId()).isEqualTo(expectedCreateOrderDto.getCustomerId());
        Assertions.assertThat(actualOrderDto.getTotalPrice()).isEqualTo(expectedPrice);
        Assertions.assertThat(actualOrderDto.getItemGroupList()).hasSameSizeAs(expectedCreateOrderDto.getItemGroupList());
        Assertions.assertThat(actualOrderDto.getItemGroupList().get(0).getItemSnapshot().getItemId()).isEqualTo(expectedCreateOrderDto.getItemGroupList().get(0).getItemId());
        Assertions.assertThat(actualOrderDto.getItemGroupList().get(0).getAmount()).isEqualTo(expectedCreateOrderDto.getItemGroupList().get(0).getAmount());
    }

}