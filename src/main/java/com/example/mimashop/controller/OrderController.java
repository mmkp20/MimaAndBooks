package com.example.mimashop.controller;

import com.example.mimashop.domain.Customer;
import com.example.mimashop.domain.Order;
import com.example.mimashop.domain.item.Item;
import com.example.mimashop.repository.OrderSearch;
import com.example.mimashop.service.CustomerService;
import com.example.mimashop.service.ItemService;
import com.example.mimashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){
        List<Customer> customers = customerService.findAll();
        List<Item> items = itemService.findItems();

        model.addAttribute("customers", customers);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String create(@RequestParam("customerId") Long customerId,
                         @RequestParam("itemId") Long itemId,
                         @RequestParam("count") int count){
        orderService.order(customerId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{id}/cancel")
    public String cancelOrder(@PathVariable("id") Long id){
        orderService.cancelOrder(id);
        return "redirect:/orders";
    }
}
