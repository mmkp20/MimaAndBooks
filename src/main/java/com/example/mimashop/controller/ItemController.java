package com.example.mimashop.controller;

import com.example.mimashop.domain.item.Book;
import com.example.mimashop.domain.item.Item;
import com.example.mimashop.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService service;

    @GetMapping("/items/new")
    public String createForm(Model model){
        log.info("Item Controller - createForm");
        model.addAttribute("itemForm", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@Valid BookForm form, BindingResult result){
        Book book = Book.createBook(form.getName(), form.getPrice(),
                form.getQuantity(), form.getAuthor(), form.getIsbn());

        service.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> itemList = service.findItems();
        model.addAttribute("items",itemList);
        return "items/itemList";
    }

    @GetMapping("/items/{id}/edit")
    public String updateItemForm(@PathVariable("id") Long itemId, Model model){
        log.info("Item Controller - updateItemFrom");

        Book item = (Book) service.findById(itemId).get();
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setAuthor(item.getAuthor());
        form.setPrice(item.getPrice());
        form.setIsbn(item.getIsbn());
        form.setQuantity(item.getQuantity());

        model.addAttribute("form",form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{id}/edit")
    public String updateItem(@PathVariable Long id, @ModelAttribute("form") BookForm form){
        log.info("Item Controller - updateItem");
        service.updateItem(id, form.getName(), form.getPrice(), form.getQuantity());

        return "redirect:/items";
    }

}


