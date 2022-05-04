package com.switchfully.eurder.item.service;

import com.switchfully.eurder.infrastructure.exception.NegativeNumberException;
import com.switchfully.eurder.infrastructure.exception.NullDescriptionException;
import com.switchfully.eurder.infrastructure.exception.NullNameException;
import com.switchfully.eurder.infrastructure.utility.FieldValidation;
import com.switchfully.eurder.item.api.dto.CreateItemDto;
import com.switchfully.eurder.item.api.dto.ItemDto;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final Logger serviceLogger = LoggerFactory.getLogger(ItemService.class);
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public ItemDto addItem(CreateItemDto createItemDto) {
        FieldValidation.stringNullSafeBlankCheck(createItemDto.getName(), new NullNameException(), "Item name null or blank!", serviceLogger);
        FieldValidation.stringNullSafeBlankCheck(createItemDto.getDescription(), new NullDescriptionException(), "Item description null or blank!", serviceLogger);
        FieldValidation.numberPositiveCheck(createItemDto.getPrice(), new NegativeNumberException(), "Item price cannot be negative!", serviceLogger);
        FieldValidation.numberPositiveCheck(createItemDto.getStockAmount(), new NegativeNumberException(), "Item stock amount cannot be negative!", serviceLogger);
        Item item = itemMapper.toItem(createItemDto);
        itemRepository.saveItem(item);
        return itemMapper.toDto(item);
    }
}
