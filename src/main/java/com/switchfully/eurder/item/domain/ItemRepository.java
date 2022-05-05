package com.switchfully.eurder.item.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {
    private final Map<String, Item> itemsById = new HashMap<>();

    public void saveItem(Item item) {
        itemsById.put(item.getItemId(), item);
    }

    public Item findById(String itemId) {
        Item itemToRetrieve = itemsById.get(itemId);
        if (itemToRetrieve == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return itemToRetrieve;
    }
}
