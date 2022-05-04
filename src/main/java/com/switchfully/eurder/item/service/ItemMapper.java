package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.api.dto.CreateItemDto;
import com.switchfully.eurder.item.api.dto.ItemDto;
import com.switchfully.eurder.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toItem(CreateItemDto createItemDto) {
        return new Item(
                createItemDto.getName(),
                createItemDto.getDescription(),
                createItemDto.getPrice(),
                createItemDto.getStockAmount()
        );
    }

    public ItemDto toDto(Item item) {
        return new ItemDto(
                item.getItemId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getStockAmount()
        );
    }
}
