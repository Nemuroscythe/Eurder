package com.switchfully.eurder.customer.api;

import com.switchfully.eurder.customer.api.dto.CustomerDto;
import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.customer.infrastructure.exception.IllegalEmailException;
import com.switchfully.eurder.customer.service.CustomerMapper;
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
class CustomerControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void givenCustomer_WhenCreateCustomer_ReturnCustomerDto() {
        //  GIVEN
        Customer expectedCustomer = new Customer("Asterix", "TheGallic", "parToutatis@gallic.com", "5, Boarstreet", "0471/00.88.71");
        CustomerDto expectedCustomerDto = customerMapper.toDto(expectedCustomer);
        //  WHEN
        CustomerDto actualCustomerDto = RestAssured
                .given()
                .port(port)
                .body(expectedCustomer)
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
        Assertions.assertThat(actualCustomerDto.getFirstName()).isEqualTo(expectedCustomerDto.getFirstName());
        Assertions.assertThat(actualCustomerDto.getLastName()).isEqualTo(expectedCustomerDto.getLastName());
        Assertions.assertThat(actualCustomerDto.getEmailAddress()).isEqualTo(expectedCustomerDto.getEmailAddress());
        Assertions.assertThat(actualCustomerDto.getAddress()).isEqualTo(expectedCustomerDto.getAddress());
        Assertions.assertThat(actualCustomerDto.getPhoneNumber()).isEqualTo(expectedCustomerDto.getPhoneNumber());
    }
//
//    @Test
//    void givenCustomerWithImproperEmailAddress_WhenCreateCustomer_ReturnCustomerDto() {
//        //  GIVEN
//        Customer expectedCustomer = new Customer("Asterix", "TheGallic", "gallic.com", "5, Boarstreet", "0471/00.88.71");
//        CustomerDto expectedCustomerDto = customerMapper.toDto(expectedCustomer);
//        //  WHEN
//        Assertions.assertThatExceptionOfType(IllegalEmailException.class).
//                isThrownBy(() -> RestAssured
//                        .given()
//                        .port(port)
//                        .body(expectedCustomer)
//                        .contentType(JSON)
//                        .when()
//                        .accept(JSON)
//                        .post("/customers")
//                        .then()
//                        .assertThat()
//                        .statusCode(HttpStatus.BAD_REQUEST.value()));
//    }
}