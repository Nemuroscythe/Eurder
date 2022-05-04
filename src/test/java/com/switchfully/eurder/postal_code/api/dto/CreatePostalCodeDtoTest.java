package com.switchfully.eurder.postal_code.api.dto;

import com.switchfully.eurder.address.api.dto.CreateAddressDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

class CreatePostalCodeDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void givenNULLPostalCodeNumber_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreatePostalCodeDto createPostalCodeDto = new CreatePostalCodeDto(null, "Gallic village");
        Set<ConstraintViolation<CreatePostalCodeDto>> violations = validator.validate(createPostalCodeDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

}