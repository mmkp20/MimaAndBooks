package com.example.mimashop.service;

import com.example.mimashop.domain.item.Item;
import com.example.mimashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("ItemService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    @Transactional
    public void saveItem(Item item) {
        repository.save(item);
    }

    @Transactional
    public void updateItem(Long id, String name, int price, int quantity){
        Item item = repository.findById(id).get();
        item.setName(name);
        item.setPrice(price);
        item.setQuantity(quantity);
    }
    public List<Item> findItems(){
        return repository.findAll();
    }

    public Optional<Item> findById(Long id){
        return repository.findById(id);
    }
}
