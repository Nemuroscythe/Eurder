package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.address.domain.Address;
import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item_group.api.dto.CreateItemGroupDto;
import com.switchfully.eurder.postal_code.domain.PostalCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

class CreateOrderDtoTest {

    private Validator validator;
    private List<CreateItemGroupDto> createItemGroupDtoList;
    private UUID customerId;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Item item = new Item("Meatball", "Boar meatballs to grow strong", 5.5, 15);
        CreateItemGroupDto createItemGroupDto = new CreateItemGroupDto(item.getItemId(), 5);
        createItemGroupDtoList = List.of(createItemGroupDto);

        PostalCode postalCode = new PostalCode("5000", "GallicVillage");
        Address address = new Address("Boarstreet", "5", postalCode);
        Customer customer = new Customer("Asterix", "TheGallic", "parToutatis@gallic.com",
                address, "0471/00.88.71");
        customerId = customer.getCustomerId();
    }

    @Test
    void givenNULLCustomerId_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateOrderDto createOrderDto = new CreateOrderDto(null, createItemGroupDtoList);
        Set<ConstraintViolation<CreateOrderDto>> violations = validator.validate(createOrderDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenNULLCreateItemGroupDtoList_ThenViolationConstraintNotEmpty() {
        //GIVEN
        CreateOrderDto createOrderDto = new CreateOrderDto(customerId, null);
        Set<ConstraintViolation<CreateOrderDto>> violations = validator.validate(createOrderDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }

    @Test
    void givenEmptyCreateItemGroupDtoList_ThenViolationConstraintNotEmpty() {
        //GIVEN
        List<CreateItemGroupDto> emptyCreateItemGroupDtoList = new ArrayList<>();
        CreateOrderDto createOrderDto = new CreateOrderDto(customerId, emptyCreateItemGroupDtoList);
        Set<ConstraintViolation<CreateOrderDto>> violations = validator.validate(createOrderDto);
        //THEN
        Assertions.assertThat(violations.isEmpty()).isFalse();
    }
}