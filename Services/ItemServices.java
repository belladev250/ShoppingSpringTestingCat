package com.example.crudtestapis.Services;
import com.example.crudtestapis.Repositories.ItemRepository;
import com.example.crudtestapis.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServices {

    @Autowired
    private ItemRepository itemRepository;


    public Product addItem(Product item) {

        Product copy = new Product(
                item.getName(),
                item.getPrice()
                item.getQuantity());
        return itemRepository.save(copy);
    }

    public List<Product> getItems() {
        List<Product> items = itemRepository.findAll();
        for(Product item:items) {
            item.setValue(item.getQuantity()*item.getPrice());
        }
        return items;

    }

    public List<Product> getItemById(int id) {
        return itemRepository.findById(id);
    }

    public void deleteItem(Product item) {
        itemRepository.delete(item);
    }




}
