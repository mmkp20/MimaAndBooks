package com.example.mimashop.domain.item;

import com.example.mimashop.domain.Category;
import com.example.mimashop.exception.OutOfStockException;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int quantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // business logic
    public void addStock(int quantity){
        this.quantity += quantity;
    }

    public void reduceStock(int quantity){
        int stock = this.quantity - quantity;
        if(stock < 0){
            throw new OutOfStockException("need more stock");
        }
        this.quantity = stock;
    }

}
