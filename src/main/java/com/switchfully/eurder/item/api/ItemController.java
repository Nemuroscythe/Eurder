package com.switchfully.eurder.item.api;

import com.switchfully.eurder.item.api.dto.CreateItemDto;
import com.switchfully.eurder.item.api.dto.ItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem (@RequestBody CreateItemDto createItemDto){
//        return itemService.addItem();
        return null;
    }
}
