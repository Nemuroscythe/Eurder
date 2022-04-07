package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.api.dto.CreateItemDto;
import com.switchfully.eurder.item.api.dto.ItemDto;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemMapper itemMapper;
    private ItemRepository itemRepository;

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public ItemDto addItem(CreateItemDto createItemDto) {
        Item item = itemMapper.toItem(createItemDto);
        itemRepository.saveItem(item);
        return itemMapper.toDto(item);
    }
}