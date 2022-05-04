package com.switchfully.eurder.address.api.dto;

import com.switchfully.eurder.postal_code.api.dto.CreatePostalCodeDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

class CreateAddressDtoTest {

    private CreatePostalCodeDto createPostalCodeDto;

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        createPostalCodeDto = new CreatePostalCodeDto("5000", "Gallic village");
    }

    @Test
    void givenNULLStreetName_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateAddressDto createAddressDto = new CreateAddressDto(null, "5", createPostalCodeDto);
        Set<ConstraintViolation<CreateAddressDto>> violations = validator.validate(createAddressDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenBlankStreetName_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateAddressDto createAddressDto = new CreateAddressDto("   ", "5", createPostalCodeDto);
        Set<ConstraintViolation<CreateAddressDto>> violations = validator.validate(createAddressDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNULLStreetNumber_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateAddressDto createAddressDto = new CreateAddressDto("BoarStree", null, createPostalCodeDto);
        Set<ConstraintViolation<CreateAddressDto>> violations = validator.validate(createAddressDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenBlankStreetNumber_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateAddressDto createAddressDto = new CreateAddressDto("BoarStreet", "  ", createPostalCodeDto);
        Set<ConstraintViolation<CreateAddressDto>> violations = validator.validate(createAddressDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNULLPostalCode_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateAddressDto createAddressDto = new CreateAddressDto("BoarStreet", "5", null);
        Set<ConstraintViolation<CreateAddressDto>> violations = validator.validate(createAddressDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

}