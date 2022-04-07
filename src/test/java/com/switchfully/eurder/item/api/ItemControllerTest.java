package com.switchfully.eurder.item.api;

import com.switchfully.eurder.item.api.dto.ItemDto;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.service.ItemMapper;
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
class ItemControllerTest {
    
    @LocalServerPort
    private int port;
    @Autowired
    private ItemMapper itemMapper;

    @Test
    void givenItem_WhenAddingItem_ThenReturnItemDto() {
        //  GIVEN
        Item expectedItem = new Item("Meatballs", "Boar meatballs to grow strong", 5.5, 15);
        ItemDto expectedItemDto = itemMapper.toDto(expectedItem);
        //  WHEN
        ItemDto actualItemDto = RestAssured
                .given()
                .port(port)
                .body(expectedItem)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(ItemDto.class);
        //  THEN
        Assertions.assertThat(actualItemDto.getItemId()).isNotBlank();
        Assertions.assertThat(actualItemDto.getName()).isEqualTo(expectedItemDto.getName());
        Assertions.assertThat(actualItemDto.getDescription()).isEqualTo(expectedItemDto.getDescription());
        Assertions.assertThat(actualItemDto.getPrice()).isEqualTo(expectedItemDto.getPrice());
        Assertions.assertThat(actualItemDto.getStockAmount()).isEqualTo(expectedItemDto.getStockAmount());
    }
}