package com.example.mimashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
public class Book extends Item{

    private String author;
    private String isbn;

    public static Book createBook (String name, int price, int quantity, String author, String isbn){
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setQuantity(quantity);
        book.setAuthor(author);
        book.setIsbn(isbn);
        return book;
    }
}
