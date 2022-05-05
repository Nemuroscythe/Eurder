package com.switchfully.eurder.item.api.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

class CreateItemDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void givenNULLName_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateItemDto createItemDto = new CreateItemDto(null, "Boar meatballs to grow strong",
                5.5, 15);
        Set<ConstraintViolation<CreateItemDto>> violations = validator.validate(createItemDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenBlankName_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateItemDto createItemDto = new CreateItemDto("  ", "Boar meatballs to grow strong",
                5.5, 15);
        Set<ConstraintViolation<CreateItemDto>> violations = validator.validate(createItemDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNULLDescription_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateItemDto createItemDto = new CreateItemDto("Meatballs", null,
                5.5, 15);
        Set<ConstraintViolation<CreateItemDto>> violations = validator.validate(createItemDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenBlankDescription_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateItemDto createItemDto = new CreateItemDto("Meatballs", "  ",
                5.5, 15);
        Set<ConstraintViolation<CreateItemDto>> violations = validator.validate(createItemDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNegativePrice_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateItemDto createItemDto = new CreateItemDto("Meatballs", "Boar meatballs to grow strong",
                -10, 15);
        Set<ConstraintViolation<CreateItemDto>> violations = validator.validate(createItemDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }


    @Test
    void givenZeroPrice_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateItemDto createItemDto = new CreateItemDto("Meatballs", "Boar meatballs to grow strong",
                0, 15);
        Set<ConstraintViolation<CreateItemDto>> violations = validator.validate(createItemDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNegativeStockAmount_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateItemDto createItemDto = new CreateItemDto("Meatballs", "Boar meatballs to grow strong",
                5.5, -30);
        Set<ConstraintViolation<CreateItemDto>> violations = validator.validate(createItemDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

}