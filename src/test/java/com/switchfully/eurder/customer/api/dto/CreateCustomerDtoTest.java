package com.switchfully.eurder.customer.api.dto;

import com.switchfully.eurder.address.api.dto.CreateAddressDto;
import com.switchfully.eurder.postal_code.api.dto.CreatePostalCodeDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

class CreateCustomerDtoTest {

    private Validator validator;
    private CreateAddressDto createAddressDto;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        CreatePostalCodeDto createPostalCodeDto = new CreatePostalCodeDto("5000", "GallicVillage");
        createAddressDto = new CreateAddressDto("Boarstreet", "5", createPostalCodeDto);
    }

    @Test
    void givenNULLFirstName_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateCustomerDto createCustomerDto = new CreateCustomerDto(null, "TheGallic",
                "parToutatis@gallic.com", createAddressDto, "0471/00.88.71");
        Set<ConstraintViolation<CreateCustomerDto>> violations = validator.validate(createCustomerDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenBlankFirstName_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateCustomerDto createCustomerDto = new CreateCustomerDto(" ", "TheGallic",
                "parToutatis@gallic.com", createAddressDto, "0471/00.88.71");
        Set<ConstraintViolation<CreateCustomerDto>> violations = validator.validate(createCustomerDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNULLLastName_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateCustomerDto createCustomerDto = new CreateCustomerDto("Asterix", null,
                "parToutatis@gallic.com", createAddressDto, "0471/00.88.71");
        Set<ConstraintViolation<CreateCustomerDto>> violations = validator.validate(createCustomerDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenBlankLastName_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateCustomerDto createCustomerDto = new CreateCustomerDto("Asterix", " ",
                "parToutatis@gallic.com", createAddressDto, "0471/00.88.71");
        Set<ConstraintViolation<CreateCustomerDto>> violations = validator.validate(createCustomerDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNULLEmailAddress_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateCustomerDto createCustomerDto = new CreateCustomerDto("Asterix", "TheGallic",
                null, createAddressDto, "0471/00.88.71");
        Set<ConstraintViolation<CreateCustomerDto>> violations = validator.validate(createCustomerDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenWrongFormatEmailAddress_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateCustomerDto createCustomerDto = new CreateCustomerDto("Asterix", "TheGallic",
                "parToutatis.com", createAddressDto, "0471/00.88.71");
        Set<ConstraintViolation<CreateCustomerDto>> violations = validator.validate(createCustomerDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNULLCreateAddressDto_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateCustomerDto createCustomerDto = new CreateCustomerDto("Asterix", "TheGallic",
                "parToutatis@gallic.com", null, "0471/00.88.71");
        Set<ConstraintViolation<CreateCustomerDto>> violations = validator.validate(createCustomerDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNULLPhoneNumber_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateCustomerDto createCustomerDto = new CreateCustomerDto("Asterix", "TheGallic",
                "parToutatis@gallic.com", createAddressDto, null);
        Set<ConstraintViolation<CreateCustomerDto>> violations = validator.validate(createCustomerDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenBlankPhoneNumber_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateCustomerDto createCustomerDto = new CreateCustomerDto("Asterix", "TheGallic",
                "parToutatis@gallic.com", createAddressDto, "  ");
        Set<ConstraintViolation<CreateCustomerDto>> violations = validator.validate(createCustomerDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

}