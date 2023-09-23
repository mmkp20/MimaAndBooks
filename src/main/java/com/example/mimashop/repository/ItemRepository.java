package com.example.mimashop.repository;

import com.example.mimashop.domain.item.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    Optional<Item> findById(Long id);
    List<Item> findAll();

}
