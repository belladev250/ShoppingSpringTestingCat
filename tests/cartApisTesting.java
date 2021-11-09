package com.example.crudtestapis.testservices;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.crudtestapis.Repositories.ItemRepository;
import com.example.crudtestapis.model.ItemModel;
import com.example.crudtestapis.Services.ItemServices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMockitoApplicationTests {

    @Autowired
    private ItemServices service;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void getItem() {
        when(itemRepository.findAll()).thenReturn(Stream
                .of(new ItemModel(376, "dress", 31, 1234),
                        new ItemModel(958, "Huy", 35,2334)).collect(Collectors.toList()));
        assertEquals(2, service.getItems().size());
    }

    @Test
    public void getItemById() {
        int id = 1;
        when(itemRepository.findById(id))
                .thenReturn(Stream.of(new ItemModel(376, "shoes", 31, 12344)).collect(Collectors.toList()));
        assertEquals(1, service.getItemById(id).size());
    }

    @Test
    public void saveItemTest() {
        ItemModel item = new ItemModel(999, "books", 33, 1222);
        when(itemRepository.save(item)).thenReturn item;
        assertEquals(item, service.addItem(item));
    }

    @Test
    public void deleteItemTest() {
        ItemModel item = new ItemModel(999, "books", 33, 12222);
        service.deleteItem(item);
        verify(itemRepository, times(1)).delete(item);
    }

}
