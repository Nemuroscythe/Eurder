package com.switchfully.eurder.order.api.dto;

import com.switchfully.eurder.item_group.api.dto.CreateItemGroupDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CreateOrderDto {

    @NotBlank(message = "customerId is null or blank")
    private final String customerId;
    @Valid
    @NotEmpty(message = "itemGroupList is null or empty")
    private final List<CreateItemGroupDto> itemGroupList;

    public CreateOrderDto(String customerId, List<CreateItemGroupDto> itemGroupList) {
        this.customerId = customerId;
        this.itemGroupList = itemGroupList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<CreateItemGroupDto> getItemGroupList() {
        return itemGroupList;
    }
}
