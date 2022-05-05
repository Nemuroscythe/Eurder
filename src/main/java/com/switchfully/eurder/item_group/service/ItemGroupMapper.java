package com.switchfully.eurder.item_group.service;

import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.item_group.api.dto.CreateItemGroupDto;
import com.switchfully.eurder.item_group.api.dto.ItemGroupDto;
import com.switchfully.eurder.item_group.domain.ItemGroup;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ItemGroupMapper {

    private final ItemRepository itemRepository;

    public ItemGroupMapper(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemGroup toItemGroup(CreateItemGroupDto createItemGroupDto) {
        return new ItemGroup(
                findItemById(createItemGroupDto.getItemId()),
                createItemGroupDto.getAmount()
        );
    }

    public List<ItemGroup> toItemGroup(List<CreateItemGroupDto> createItemGroupDtoList) {
        return createItemGroupDtoList.stream()
                .map(this::toItemGroup)
                .toList();
    }

    public ItemGroupDto toDto(ItemGroup itemGroup) {
        return new ItemGroupDto(
                itemGroup.getId(),
                itemGroup.getItemSnapshot(),
                itemGroup.getAmount(),
                itemGroup.getShippingDate(),
                itemGroup.getGroupPrice());
    }

    public List<ItemGroupDto> toDto(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map(this::toDto)
                .toList();
    }

    public Item findItemById(UUID itemId) {
        return itemRepository.findById(itemId).get();
    }
}
