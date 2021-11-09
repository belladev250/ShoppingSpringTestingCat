package com.example.crudtestapis.Controllers;

import com.example.crudtestapis.Services.ItemServices;
import com.example.crudtestapis.model.ItemModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {


    private ItemServices itemServices;

    @PostMapping(value = "/save")
    public ItemModel saveItem(@RequestBody ItemModel item) {
        return itemServices.addItem(item);
    }

    @GetMapping("/getItems")
    public List<ItemModel> findAllItems() {
        return itemServices.getItems();
    }

    @GetMapping("/getItemById/{id}")
    public List<ItemModel> findItemById(@PathVariable int id) {
        return itemServices.getItemById(id);
    }

    @DeleteMapping(value="/remove-item")
    public ItemModel removeItem(@RequestBody ItemModel item) {
        itemServices.deleteItem(item);
        return item;
    }


}
