package com.switchfully.eurder.item_group.api.dto;

import com.switchfully.eurder.item.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

class CreateItemGroupDtoTest {

    private Validator validator;
    private String itemId;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Item item = new Item(null, "Boar meatballs to grow strong", 5.5, 15);
        itemId = item.getItemId();
    }

    @Test
    void givenNULLItemId_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateItemGroupDto createItemGroupDto = new CreateItemGroupDto(null, 5);
        Set<ConstraintViolation<CreateItemGroupDto>> violations = validator.validate(createItemGroupDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNegativeAmount_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateItemGroupDto createItemGroupDto = new CreateItemGroupDto(itemId, -10);
        Set<ConstraintViolation<CreateItemGroupDto>> violations = validator.validate(createItemGroupDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenZeroAmount_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateItemGroupDto createItemGroupDto = new CreateItemGroupDto(itemId, 0);
        Set<ConstraintViolation<CreateItemGroupDto>> violations = validator.validate(createItemGroupDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

}