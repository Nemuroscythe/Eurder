package com.switchfully.eurder.customer.api;

import com.switchfully.eurder.address.api.dto.CreateAddressDto;
import com.switchfully.eurder.customer.api.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.api.dto.CustomerDto;
import com.switchfully.eurder.customer.service.CustomerMapper;
import com.switchfully.eurder.postal_code.api.dto.CreatePostalCodeDto;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class CustomerControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void givenCustomer_WhenCreateCustomer_ThenReturnCustomerDto() {
        //  GIVEN
        CreateCustomerDto expectedCreateCustomerDto = new CreateCustomerDto("Asterix", "TheGallic", "parToutatis@gallic.com",
                new CreateAddressDto("Boarstreet", "5", new CreatePostalCodeDto("5000", "GallicVillage")),
                "0471/00.88.71");
        //  WHEN
        CustomerDto actualCustomerDto = RestAssured
                .given()
                .port(port)
                .body(expectedCreateCustomerDto)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(CustomerDto.class);
        //  THEN
        Assertions.assertThat(actualCustomerDto.getCustomerId()).isNotBlank();
        Assertions.assertThat(actualCustomerDto.getFirstName()).isEqualTo(expectedCreateCustomerDto.getFirstName());
        Assertions.assertThat(actualCustomerDto.getLastName()).isEqualTo(expectedCreateCustomerDto.getLastName());
        Assertions.assertThat(actualCustomerDto.getEmailAddress()).isEqualTo(expectedCreateCustomerDto.getEmailAddress());
        Assertions.assertThat(actualCustomerDto.getAddress().getStreetName()).isEqualTo(expectedCreateCustomerDto.getAddress().getStreetName());
        Assertions.assertThat(actualCustomerDto.getAddress().getStreetNumber()).isEqualTo(expectedCreateCustomerDto.getAddress().getStreetNumber());
        Assertions.assertThat(actualCustomerDto.getAddress().getPostalCode().getPostalCodeNumber()).isEqualTo(expectedCreateCustomerDto.getAddress().getPostalCode().getPostalCodeNumber());
        Assertions.assertThat(actualCustomerDto.getAddress().getPostalCode().getCity()).isEqualTo(expectedCreateCustomerDto.getAddress().getPostalCode().getCity());
        Assertions.assertThat(actualCustomerDto.getPhoneNumber()).isEqualTo(expectedCreateCustomerDto.getPhoneNumber());
    }

    @Test
    void givenCustomerWithNullFields_WhenCreateCustomer_ThenBadRequest() {
        //  GIVEN
        CreateCustomerDto expectedCreateCustomerDto = new CreateCustomerDto(null,null,null,
                null,null);
        //  WHEN
        //  THEN
        RestAssured
                .given()
                .port(port)
                .body(expectedCreateCustomerDto)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}