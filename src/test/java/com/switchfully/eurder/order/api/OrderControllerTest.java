package com.switchfully.eurder.order.api;

import com.switchfully.eurder.address.domain.Address;
import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.item_group.api.dto.CreateItemGroupDto;
import com.switchfully.eurder.order.api.dto.CreateOrderDto;
import com.switchfully.eurder.order.api.dto.OrderDto;
import com.switchfully.eurder.postal_code.domain.PostalCode;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.UUID;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class OrderControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;

    private Item itemInDB;
    private Customer customerInDB;

    @BeforeEach
    void setUp() {
        customerInDB = new Customer("Asterix", "TheGallic", "parToutatis@gallic.com",
                new Address("Boarstreet", "5", new PostalCode("5000", "GallicVillage")),
                "0471/00.88.71");
        customerRepository.save(customerInDB);


        itemInDB = new Item("Magic potion", "The famous potion brewed by Panoramix", 1000, 1);
        itemRepository.save(itemInDB);
    }

    @Nested
    @DisplayName("Order integration tests")
    class OrderIntegrationTest {

        @Test
        void givenItemGroupListAndACustomerID_WhenOrderItems_ThenReturnOrderDtoContainingTheOnlyOrderedItemGroup() {
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
            Assertions.assertThat(actualOrderDto.getCustomerId()).isEqualTo(expectedCreateOrderDto.getCustomerId());
            Assertions.assertThat(actualOrderDto.getTotalPrice()).isEqualTo(expectedPrice);
            Assertions.assertThat(actualOrderDto.getItemGroupList()).hasSameSizeAs(expectedCreateOrderDto.getItemGroupList());
            Assertions.assertThat(actualOrderDto.getItemGroupList().get(0).getItemSnapshot().getItemId()).isEqualTo(expectedCreateOrderDto.getItemGroupList().get(0).getItemId());
            Assertions.assertThat(actualOrderDto.getItemGroupList().get(0).getAmount()).isEqualTo(expectedCreateOrderDto.getItemGroupList().get(0).getAmount());

            Assertions.assertThat(customerRepository.existsById(actualOrderDto.getCustomerId())).isTrue();
        }

        @Test
        void givenOrderWithInvalidFields_WhenOrderItems_ThenBadRequest() {
            //  GIVEN
            CreateOrderDto expectedCreateOrderDto = new CreateOrderDto(customerInDB.getCustomerId(), null);
            //  WHEN
            RestAssured
                    .given()
                    .port(port)
                    .body(expectedCreateOrderDto)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/orders")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void givenOrderWithNotExistingCustomerId_WhenOrderItems_ThenBadRequest() {
            //  GIVEN
            CreateItemGroupDto expectedCreateItemGroupDto = new CreateItemGroupDto(itemInDB.getItemId(), 5);
            CreateOrderDto expectedCreateOrderDto = new CreateOrderDto(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                    List.of(expectedCreateItemGroupDto));
            //  WHEN
            RestAssured
                    .given()
                    .port(port)
                    .body(expectedCreateOrderDto)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/orders")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    @DisplayName("Item group integration tests")
    class ItemGroupIntegrationTest {

        @Test
        void givenItemGroupWithNotExistingItemId_WhenOrderItems_ThenBadRequest() {
            //  GIVEN
            CreateItemGroupDto expectedCreateItemGroupDto = new CreateItemGroupDto(UUID.fromString("123e4560-e89b-12d3-a456-426614174000"), 1);
            CreateOrderDto expectedCreateOrderDto = new CreateOrderDto(customerInDB.getCustomerId(), List.of(expectedCreateItemGroupDto));
            //  WHEN
            RestAssured
                    .given()
                    .port(port)
                    .body(expectedCreateOrderDto)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/orders")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void givenItemGroupWithInvalidFields_WhenOrderItems_ThenBadRequest() {
            //  GIVEN
            CreateItemGroupDto expectedCreateItemGroupDto = new CreateItemGroupDto(itemInDB.getItemId(), -5);
            CreateOrderDto expectedCreateOrderDto = new CreateOrderDto(customerInDB.getCustomerId(), List.of(expectedCreateItemGroupDto));
            //  WHEN
            RestAssured
                    .given()
                    .port(port)
                    .body(expectedCreateOrderDto)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/orders")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }
}