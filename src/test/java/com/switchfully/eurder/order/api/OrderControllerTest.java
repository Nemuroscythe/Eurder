package com.switchfully.eurder.order.api;

import com.switchfully.eurder.customer.api.dto.CustomerDto;
import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.customer.service.CustomerMapper;
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
    void givenItemGroup_WhenOrderItems_ThenReturnOrderDtoContainingTheOrderedItem() {
        //  GIVEN
        Customer expectedCustomer = new Customer("Asterix", "TheGallic", "parToutatis@gallic.com", "5, Boarstreet", "0471/00.88.71");
        customerRepository.saveCustomer(expectedCustomer);
        Item expectedItem = new Item("Magic potion", "The famous potion brewed by Panoramix", 1000, 1);
        ItemGroup expectedItemGroup = new ItemGroup(expectedItem,1);
        Order expectedOrder = new Order(expectedCustomer.getCustomerId(), expectedItemGroup);
        OrderDto expectedOrderDto = orderMapper.toDto(expectedOrder);
        //  WHEN
        OrderDto actualOrderDto = RestAssured
                .given()
                .port(port)
                .body(expectedCustomer)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(OrderDto.class);
        //  THEN
        Assertions.assertThat(actualOrderDto).isEqualTo(expectedOrderDto);
    }
}