package com.switchfully.eurder.item.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {
    private Map<String, Item> itemsById = new HashMap<>();

    public void saveItem(Item item) {
        itemsById.put(item.getItemId(), item);
    }
}
